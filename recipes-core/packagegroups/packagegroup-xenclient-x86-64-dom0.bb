DESCRIPTION = "All packages required for XenClient dom0"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6      \
                    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit xenclient
inherit packagegroup

RDEPENDS_${PN} = " \
    xenclient-root-ro \
    kernel-modules \
    v4v-module \
    linux-firmware-iwlwifi-135-6 \
    linux-firmware-iwlwifi-3160-7 \
    linux-firmware-iwlwifi-3160-8 \
    linux-firmware-iwlwifi-3160-9 \
    linux-firmware-iwlwifi-6000-4 \
    linux-firmware-iwlwifi-6000g2a-5 \
    linux-firmware-iwlwifi-6000g2a-6 \
    linux-firmware-iwlwifi-6000g2b-5 \
    linux-firmware-iwlwifi-6000g2b-6 \
    linux-firmware-iwlwifi-6050-4 \
    linux-firmware-iwlwifi-6050-5 \
    linux-firmware-iwlwifi-7260-7 \
    linux-firmware-iwlwifi-7260-8 \
    linux-firmware-iwlwifi-7260-9 \
    linux-firmware-iwlwifi-7265-8 \
    linux-firmware-iwlwifi-7265-9 \
    linux-firmware-bnx2 \
"

# OE upgrade - temporarly disabled:

# gconf-dbus \
# xserver-xorg \
# xf86-video-intel-xenclient-dom0 \
# xf86-video-vesa-xenclient-dom0 \
#
