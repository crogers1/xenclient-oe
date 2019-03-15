DESCRIPTION = "XenClient toolstack"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=321bf41f280cf805086dd5a720b37785"
DEPENDS += "xen xz"

inherit autotools-brokensep
inherit ${@"xenclient-simple-deb"if(d.getVar("MACHINE",1)=="xenclient-nilfvm")else("null")}

PACKAGES = "${PN}-doc ${PN}-locale ${PN}-dev ${PN}"

# This is a little hybrid between usual package and findlib installation.
# findlib.bbclass redefines FILES, as ocaml packages are installed in
# ${sitelibdir} canonically.
FILES_${PN} = " \
    ${sysconfdir} \
"

DEB_SUITE = "wheezy"
DEB_ARCH = "i386"

DEB_NAME = "nilfvm-xenclient-toolstack"
DEB_DESC="The nilfvm XenClient toolstack package"
DEB_DESC_EXT="This package provides the  nilfvm XenClient toolstack scrips."
DEB_SECTION="misc"
DEB_PKG_MAINTAINER = "Citrix Systems <customerservice@citrix.com>"

PV = "0+git${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://${OPENXT_GIT_MIRROR}/toolstack.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH}	\
           file://vif \
           ${@bb.utils.contains('DISTRO_FEATURES', 'blktap2', '', 'file://0001-blktap3-move-physical-device-xenstore-node-creation-.patch', d)} \
           "

PACKAGE_ARCH = "${MACHINE_ARCH}"
S = "${WORKDIR}/git"

do_configure_xenclient-nilfvm() {
        :
}

do_compile_xenclient-nilfvm() {
        :
}

# Don't run any of the ocaml compile stuff
do_configure() {
}

do_compile() {
}

do_install() {
        rm -f ${D}/etc/xen/scripts/vif

        install -d ${D}/etc/xen/scripts
        install -m 0755 ${WORKDIR}/git/scripts/* ${D}/etc/xen/scripts
        install -m 0755 ${WORKDIR}/vif ${D}/etc/xen/scripts/vif

}

do_install_append_xenclient-nilfvm() {
	## to generate deb package
	DEB_DO_NOT_INCLUDE="usr/bin/ usr/lib/"
	do_simple_deb_package
}
