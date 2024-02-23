FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}-${PV}:"

SRC_URI += " \
    file://openxt-menus.patch \
    file://disable-available-to-all-users-checkbox.patch \
    file://always-use-psk-hash.patch \
    file://disable-auto-ethernet.patch \
    file://meson-popup-menu.patch \
    file://org.openxt.nmapplet.xml \
"

#file://disable-show-password.patch
#file://default-certs-dir.patch
do_configure:prepend() {
    gdbus-codegen --generate-c-code ${S}/src/popup-menu --c-namespace OpenXT --interface-prefix org.openxt. ${WORKDIR}/org.openxt.nmapplet.xml
}
