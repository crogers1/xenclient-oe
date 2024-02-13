PV = "0+git${SRCPV}"
SRC_URI = "git://github.com/crogers1/manager.git;protocol=https;branch=ghc8"
SRCREV = "${AUTOREV}"

# needed for ghc-provided libffi
RDEPENDS:${PN} += "\
    ghc-runtime \
"
