DESCRIPTION = "QT-based JSON library"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://github.com/QtMark/qmjson.git;protocol=git;branch=master"

SRCREV = "7cee294cc32650b3bcb29d386206065145021b44"

S = "${WORKDIR}/git"
PV = "1.0+git${SRCPV}"

inherit qmake5

DEPENDS = "qtbase"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

PR="r2"
