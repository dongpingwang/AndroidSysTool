package com.wdp.sys

import com.wdp.sys.util.SystemPropertiesProxy

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
class DeviceCtrl {
    private companion object {
        const val TAG = "DeviceCtrl"
    }

    fun shutdown() {
        SystemPropertiesProxy.set("sys.powerctl", "shutdown")
    }

    fun reboot() {
        SystemPropertiesProxy.set("sys.powerctl", "reboot")
    }

    fun reset() {

    }
}