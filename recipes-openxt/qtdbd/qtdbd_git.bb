DESCRIPTION = "QT replacement for dbd and db-tools"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://${OPENXT_GIT_MIRROR}/qtdbd.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH} \
           file://qtdbd.patch;patch=1 \
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
    /etc/init.d/dbd \
    /usr/bin/dbd \
    /usr/share/xenclient/db.default \
    "

FILES_${PN}-tools = "\
    /usr/bin/db-cat \
    /usr/bin/db-dump \
    /usr/bin/db-exists \
    /usr/bin/db-inject \
    /usr/bin/db-ls \
    /usr/bin/db-nodes \
    /usr/bin/db-read \
    /usr/bin/db-rm \
    /usr/bin/db-write \
    "

FILES_${PN}-tools-v4v-wrappers = "\
    /usr/bin/db-cat-dom0 \
    /usr/bin/db-dump-dom0 \
    /usr/bin/db-exists-dom0 \
    /usr/bin/db-inject-dom0 \
    /usr/bin/db-ls-dom0 \
    /usr/bin/db-nodes-dom0 \
    /usr/bin/db-read-dom0 \
    /usr/bin/db-rm-dom0 \
    /usr/bin/db-write-dom0 \
    "

FILES_${PN}-unittests = "\
    /usr/bin/qtdbd-unittests \
    "

FILES_${PN}-perftest = "\
    /usr/bin/dbd-perftest \
    "

PR="r19"
