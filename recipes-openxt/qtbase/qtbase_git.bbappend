PACKAGECONFIG = " \
    release \
    libpng \
    udev \
    dbus \
    tools \
    libs \
"


PACKAGECONFIG[libinput] = ""

QT_CONFIG_FLAGS += " \
    -dbus \
    -no-xcb \
"

PACKAGES_remove = "${PN}-examples-dev"

PACKAGECONFIG_CONFARGS += " \
    -no-accessibility \
    -no-cups \
    -no-qml-debug \
    -no-sql-mysql \
    -no-sql-sqlite \
    -no-openssl \
    -no-eglfs \
    -no-opengl \
    -no-xcb \
    -no-icu \
    -release \
    -no-iconv \
    -nomake examples \
    -nomake tests \
"

DEPENDS += "libpng"
PARALLEL_MAKE = ""
