SUMMARY = "Tools for TPM2."
DESCRIPTION = "tpm2.0-tools"
SECTION = "tpm"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91b7c548d73ea16537799e8060cea819"
DEPENDS = "tpm2.0-tss openssl curl"
SRC_URI = "git://github.com/crogers1/tpm2.0-tools.git;protocol=git;branch=upstream-rebase-v3;name=tpm2.0-tools;destsuffix=tpm2.0-tools"

S = "${WORKDIR}/tpm2.0-tools"
# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

EXTRA_OECONF = "--with-tcti-device --without-tcti-socket"

inherit autotools pkgconfig

do_configure_prepend () {
	bbnote "test"
	# execute the bootstrap script
	currentdir=$(pwd)
	cd ${S}
	./bootstrap --force
	cd ${currentdir}
	oe_runconf
}
