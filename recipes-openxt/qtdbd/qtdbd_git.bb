DESCRIPTION = "QT replacement for dbd and db-tools"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://github.com/cjp256/qtdbd.git;protocol=git;branch=master \
           file://qtdbd.initscript \
           file://db.default \
           file://db-cat-dom0 \
           file://db-dump-dom0 \
           file://db-exists-dom0 \
           file://db-inject-dom0 \
           file://db-ls-dom0 \
           file://db-nodes-dom0 \
           file://db-read-dom0 \
           file://db-rm-dom0 \
           file://db-write-dom0 \
"

SRCREV = "master"

S = "${WORKDIR}/git"
PV = "4.0+git${SRCPV}"

inherit qmake5 update-rc.d

INITSCRIPT_NAME = "dbd"
INITSCRIPT_PARAMS = "defaults 25"

DEPENDS += "dbus qtbase qmjson xen"

EXTRA_OEMAKE += " INSTALL_ROOT=${D} "

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/qtdbd.initscript ${D}${sysconfdir}/init.d/dbd
    
    install -m 0755 -d ${D}/usr/share/xenclient
    install -m 0644 ${WORKDIR}/db.default ${D}/usr/share/xenclient/db.default
    
    install -m 0755 -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/db-cat-dom0 ${D}/usr/bin/db-cat-dom0
    install -m 0755 ${WORKDIR}/db-dump-dom0 ${D}/usr/bin/db-dump-dom0
    install -m 0755 ${WORKDIR}/db-exists-dom0 ${D}/usr/bin/db-exists-dom0
    install -m 0755 ${WORKDIR}/db-inject-dom0 ${D}/usr/bin/db-inject-dom0
    install -m 0755 ${WORKDIR}/db-ls-dom0 ${D}/usr/bin/db-ls-dom0
    install -m 0755 ${WORKDIR}/db-nodes-dom0 ${D}/usr/bin/db-nodes-dom0
    install -m 0755 ${WORKDIR}/db-read-dom0 ${D}/usr/bin/db-read-dom0
    install -m 0755 ${WORKDIR}/db-rm-dom0 ${D}/usr/bin/db-rm-dom0
    install -m 0755 ${WORKDIR}/db-write-dom0 ${D}/usr/bin/db-write-dom0

}

PACKAGES += "\
    ${PN}-tools \
    ${PN}-unittests \
    ${PN}-perftest \
    ${PN}-tools-v4v-wrappers \
    "

FILES_${PN} = "\
    ${sysconfdir}/init.d/dbd \
    ${bindir}/dbd \
    ${datadir}/xenclient/db.default \
    "

FILES_${PN}-tools = "\
    ${bindir}/db-cat \
    ${bindir}/db-dump \
    ${bindir}/db-exists \
    ${bindir}/db-inject \
    ${bindir}/db-ls \
    ${bindir}/db-nodes \
    ${bindir}/db-read \
    ${bindir}/db-rm \
    ${bindir}/db-write \
    "

FILES_${PN}-tools-v4v-wrappers = "\
    ${bindir}/db-cat-dom0 \
    ${bindir}/db-dump-dom0 \
    ${bindir}/db-exists-dom0 \
    ${bindir}/db-inject-dom0 \
    ${bindir}/db-ls-dom0 \
    ${bindir}/db-nodes-dom0 \
    ${bindir}/db-read-dom0 \
    ${bindir}/db-rm-dom0 \
    ${bindir}/db-write-dom0 \
    "

FILES_${PN}-unittests = "\
    ${bindir}/qtdbd-unittests \
    "

FILES_${PN}-perftest = "\
    ${bindir}/dbd-perftest \
    "

PR="r19"
