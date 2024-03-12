LICENSE = "GPL-2.0-only & LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac  \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = " \
    xen \
    alsa-lib \
    pciutils \
    libpng \
    xen-tools \
    libargo \
    zlib \
    libcap-ng \
    pixman \
    ninja-native \
    meson-native \
    bison-native \
    glib-2.0 \
"

PV = "6.2.0"

# Leave this in it's own definition so that it can be replaced with a local
# tarball (the QEMU download speed is terrible). If this is combined with the
# other patches, it doesn't seem to work
SRC_URI = "https://download.qemu.org/qemu-${PV}.tar.bz2"

# QEMU Patch Queue
SRC_URI += "file://compile-time-stubdom-flag.patch \
            file://generic-xenstore-extensions.patch \
            file://readonly-ide.patch \
            file://hvm-param-dm-domain.patch \
            file://acpi.patch \
            file://argo-configure.patch \
            file://atapi-pass-through.patch \
            file://audio-policy.patch \
            file://msix-cap-disable.patch \
            file://openxtaudio.patch \
            file://nic-link-state-propagation.patch \
            file://acpi-pm-feature.patch \
            file://maintain-time-offset.patch \
            file://openxt-misc-fixes.patch \
            file://qmp-argo-char-driver.patch \
            file://write-acpi-state-to-xenstore.patch \
            file://set-blockdev-ro.patch \
            file://block-remove-unused-block-format-support.patch \
            file://round-pci-region-sizes.patch \
            file://openxt-apply-config-changes.patch \
            "

SRC_URI[md5sum] = "f276491d6821f81b1c405b7a0c3c851e"
SRC_URI[sha256sum] = "2fde1a30a7dcc15dde0875319ecaf3dc249072dee46ba7ad1f7c8df1faece93f"

S = "${WORKDIR}/qemu-${PV}"

inherit autotools-brokensep pkgconfig python3native

require recipes-extended/qemu-dm/qemu-dm-vglass.inc

# Include a minimal amount of blobs by default.
FILES:${PN} += "/usr/share/qemu/keymaps/en-us       \
                /usr/share/qemu/keymaps/commons     \
                /usr/share/qemu/keymaps/modifiers   \
                "
FILES:${PN}-dbg += "/usr/lib/.debug/* /usr/libexec/.debug/*"
FILES:${PN}-extra-keymaps = "/usr/share/qemu/keymaps/*"
FILES:${PN}-extra-roms = "/usr/share/qemu/*"
INSANE_SKIP:${PN}-extra-roms = "arch"
FILES:${PN}-utils = "/usr/libexec/* \
                     /usr/bin/qemu-pr-helper \
"
FILES:${PN}-icons = "/usr/share/icons/hicolor/*"
PACKAGES += "${PN}-icons"
PACKAGES += "${PN}-extra-keymaps ${PN}-extra-roms"
PACKAGES =+ "${PN}-utils"

EXTRA_OECONF += " \
    --target-list=i386-softmmu \
    --disable-auth-pam \
    --disable-bochs \
    --disable-bzip2 \
    --disable-cap-ng \
    --disable-capstone \
    --disable-cloop \
    --disable-curl \
    --disable-curses \
    --disable-dmg \
    --disable-docs \
    --disable-gcrypt \
    --disable-gnutls \
    --disable-guest-agent \
    --disable-iconv \
    --disable-kvm \
    --disable-libusb \
    --disable-live-block-migration \
    --disable-lzo \
    --disable-nettle \
    --disable-opengl \
    --disable-parallels \
    --disable-pvrdma \
    --disable-qcow1 \
    --disable-qed \
    --disable-qom-cast-debug \
    --disable-rdma \
    --disable-replication \
    --disable-sdl \
    --disable-slirp \
    --disable-spice \
    --disable-strip \
    --disable-tcg \
    --disable-tcg-interpreter \
    --disable-tools \
    --disable-tpm \
    --disable-usb-redir \
    --disable-vdi \
    --disable-vhost-crypto \
    --disable-vhost-net \
    --disable-vhost-scsi \
    --disable-vhost-user \
    --disable-vhost-vsock \
    --disable-virtfs \
    --disable-vnc \
    --disable-vvfat \
    --enable-atapi-pt \
    --enable-atapi-pt-argo \
    --enable-debug-info \
    --enable-pie \
    --enable-readonly-ide \
    --enable-trace-backends=nop \
    --enable-werror \
    --enable-xen \
    --enable-openxt-audio \
    --extra-cflags="${CFLAGS}" \
"

do_configure(){
    ${S}/configure --prefix=${prefix} --sysconfdir=${sysconfdir} ${EXTRA_OECONF}
}

EXTRA_OEMAKE += "AUTOCONF_HOST='--host=`arch`' DESTDIR=${D} STRIP=''"

INC_PR = "r17"

do_configure:append() {
	sed -i -s '/^CONFIG_L2TPV3=y/d' ${S}/build/config-host.mak
}
