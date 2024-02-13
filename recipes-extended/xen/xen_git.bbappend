SRCREV = "${AUTOREV}"
XEN_REL = "4.18"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a1e216f80b6d8da95fec897d0dbec9"
LICENSE = "GPLv2"

# OpenXT's Xen recipes share a common patchqueue so reset SRC_URI
SRC_URI = "git://xenbits.xen.org/xen.git;branch=stable-4.16"

require xen-common.inc
require xen-openxt.inc

DEFAULT_PREFERENCE = "1"

do_deploy() {
    XEN_FULLVERSION=$(oe_runmake -C ${S}/xen xenversion --no-print-directory)
    FLASK_POLICY_FILE="xenpolicy-${XEN_FULLVERSION}"

    install -d ${DEPLOYDIR}

    # Install the flask policy in the deploy directory if it exists
    if [ -f ${D}/boot/${FLASK_POLICY_FILE} ]; then
        install -m 0644 ${D}/boot/${FLASK_POLICY_FILE} ${DEPLOYDIR}
        ln -sf ${FLASK_POLICY_FILE} ${DEPLOYDIR}/xenpolicy-${MACHINE}
    fi
}
addtask deploy after do_install do_populate_sysroot before do_build
