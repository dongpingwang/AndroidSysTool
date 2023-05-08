package com.wdp.sys

import com.wdp.sys.util.SystemPropertiesProxy

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
        return SystemPropertiesProxy.getString("ro.firmware.version")
    }
}