# intel-microcode is MACHINE specific for some reason.

# TODO: look into using cpio archive instead, which is the only
# artifact that's generated on kirkstone. according to the xen
# docs, loading from the cpio should also be supported from the
# multiboot header.
do_compile:prepend() {
	${STAGING_DIR_NATIVE}${sbindir_native}/iucode_tool \
		${UCODE_FILTER_PARAMETERS} \
		--overwrite \
		--write-to=${WORKDIR}/microcode_${PV}.bin \
		${S}/intel-ucode/* ${S}/intel-ucode-with-caveats/*
}

# Since dom0 is shipped as a full image, the installer copies relevant files
# from /boot to the boot partition.
do_install:append() {
    install -d "${D}/boot"
    # intel-microcode recipe installs in ${WORKDIR}.
    install "${WORKDIR}/microcode_${PV}.bin" "${D}/boot/microcode_${PV}.bin"
    ln -sfr "${D}/boot/microcode_${PV}.bin" "${D}/boot/microcode_intel.bin"
}

# Override do_deploy to suit OpenXT existing bootstrap.
do_deploy() {
    # intel-microcode recipe installs in ${WORKDIR}.
    install "${WORKDIR}/microcode_${PV}.bin" "${DEPLOYDIR}/microcode_intel.bin"
}

FILES:${PN} += "/boot"
