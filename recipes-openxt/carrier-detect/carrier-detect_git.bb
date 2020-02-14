DESCRIPTION = "network interface carrier detect program"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4641e94ec96f98fabc56ff9cc48be14b"
DEPENDS = "libnl"

SRC_URI = "git://${OPENXT_GIT_MIRROR}/xctools.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/carrier-detect"

inherit autotools pkgconfig