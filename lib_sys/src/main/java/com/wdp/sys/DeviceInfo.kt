package com.wdp.sys

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
class DeviceInfo {

    private companion object {
        const val TAG = "DeviceInfo"
    }

    fun getSerialNumber(): String? {
        return SystemPropertiesProxy.getString("ro.serialno")
    }

    fun getVersion(): String? {
        var version = SystemPropertiesProxy.getString("ro.firmware.version")
        if (version?.isNotEmpty() == true) {
            return version
        }
        version = SystemPropertiesProxy.getString("ro.product.version")
        return version
    }
}