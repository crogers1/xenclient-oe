FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
    file://xen-fix-print-format.patch \
    file://xen-log-to-ioport-0xe9.patch \
    file://xen-fix-vbe-win8.patch \
"

do_install:append() {
    install -m 0644 VGABIOS-lgpl-latest-debug.bin ${D}${datadir}/firmware/${BPN}-${PV}.debug.bin
    install -m 0644 VGABIOS-lgpl-latest-cirrus-debug.bin ${D}${datadir}/firmware/${BPN}-${PV}.cirrus.debug.bin
}
