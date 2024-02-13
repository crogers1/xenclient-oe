SUMMARY = "Tools for TPM2."
DESCRIPTION = "tpm2-tools"
SECTION = "tpm"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/doc/LICENSE;md5=a846608d090aa64494c45fc147cc12e3"
DEPENDS = "tpm2-tss openssl curl autoconf-archive pkgconfig libgcrypt"

SRCREV = "6976f7a862c4215704d9b824d58d48be4048bd2a"
SRC_URI = "git://github.com/01org/tpm2-tools.git;protocol=https;branch=5.1.X \
"
#    file://tpm2-tools-lib-support.patch
#    file://tpm2-sealing-support.patch
#    file://tpm2-unsealing-support.patch
#    file://tpm2-extendpcr-support.patch
#    file://tpm2-nvlist-drop-ntoh.patch

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure:prepend() {
    pushd ${S}
    AUTORECONF=true ./bootstrap
    popd
}

PACKAGES =+ "tpm2-tools-initrd"
FILES:${PN}-initrd = " \
    ${bindir}/tpm2_pcrlist \
    ${bindir}/tpm2_extendpcr \
"
RDEPENDS:${PN} += "${PN}-initrd"

PACKAGES += "${PN}-bc"
FILES:${PN}-bc = " \
    ${datadir}/bash-completion/* \
"

RPROVIDES:${PN} += "${PN}-initrd"
