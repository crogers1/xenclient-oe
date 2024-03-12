DESCRIPTION = "Plex86/Bochs LGPL VGABios"
HOMEPAGE = "http://www.nongnu.org/vgabios/"
LICENSE = "LGPL-2.1-only"
SECTION = "firmware"

DEPENDS = "dev86-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=fae731a3adbc92fd8bb1730d1f2455bc"

SRC_URI =  "git://github.com/bochs-emu/VGABIOS;branch=master;protocol=http \
            file://build-cc.patch"

SRCREV = "6f4a83ccf8dff2e6ad0b683a4fabad50ac3de121"
S="${WORKDIR}/git/vgabios"
EXTRA_OEMAKE = "HOSTCC="${BUILD_CC}""

do_install() {
    install -d ${D}${datadir}/firmware
    for file in VGABIOS*.bin; do
        target=$(echo $file | sed s/VGABIOS-lgpl-latest/${BP}/)
        install -m0644 $file ${D}${datadir}/firmware/$target
    done
}

FILES:${PN} = "${datadir}/firmware/${BP}*.bin"
FILES:${PN}-dbg = "${datadir}/firmware/${BP}*.debug.bin"
