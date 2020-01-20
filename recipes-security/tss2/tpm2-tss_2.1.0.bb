SUMMARY = "Software stack for TPM2."
DESCRIPTION = "tpm2-tss for interfacing with tpm2.0 device"
SECTION = "tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0b1d631c4218b72f6b05cb58613606f4"

DEPENDS = "autoconf-archive autoconf pkgconfig libgcrypt gnome-common"

SRCREV = "e76cac95bb737b1705426e4b714e28e628015f57"

SRC_URI = " \
    file://0001-build-update-for-ax_code_coverage.m4-version-2019.01.patch \
    git://github.com/01org/tpm2-tss.git;protocol=git;branch=2.1.x \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGES =+ " \
    resourcemgr \
"
FILES_resourcemgr = " \
    ${sbindir}/resourcemgr \
"

do_configure_prepend () {
    # Creates the src_vars.mk file used by automake to handle source-files for
    # each component. Modified to not call autotools and let OE handle that.
    cd ${S}
    AUTORECONF=true ./bootstrap
    cd -
}
