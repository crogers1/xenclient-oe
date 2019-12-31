FILESEXTRAPATHS_prepend := "${THISDIR}/dialog:"

SRC_URI += "\
    file://dialogrc \
"

FILES_${PN} += "/root/.dialogrc"

do_install_append() {
    install -d ${D}/root
    install -m 755 ${WORKDIR}/dialogrc ${D}/root/.dialogrc
}
