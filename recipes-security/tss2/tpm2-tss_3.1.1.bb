SUMMARY = "Software stack for TPM2."
DESCRIPTION = "tpm2-tss for interfacing with tpm2.0 device"
SECTION = "tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS = "autoconf-archive autoconf pkgconfig libgcrypt gnome-common openssl json-c curl"

SRCREV = "c5f3aed26a0e00215a246910d8521b1c8da34783"

SRC_URI = " \
    git://github.com/01org/tpm2-tss.git;protocol=https;branch=3.1.x \
    file://oe-fix.patch \
"
#    file://0001-build-update-for-ax_code_coverage.m4-version-2019.01.patch

S = "${WORKDIR}/git"

inherit autotools pkgconfig useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-r -g tss tss"
GROUPADD_PARAM:${PN} = "-r tss"

PACKAGES =+ " \
    resourcemgr \
"
FILES:resourcemgr = " \
    ${sbindir}/resourcemgr \
"

do_configure:prepend () {
    # Creates the src_vars.mk file used by automake to handle source-files for
    # each component. Modified to not call autotools and let OE handle that.
    cd ${S}
    AUTORECONF=true ./bootstrap
    cd -
}

do_install:append() {
   rm -rf ${D}/var/run/*
}
