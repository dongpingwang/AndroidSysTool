package com.wdp.sys

import android.content.Intent
import com.wdp.sys.util.ContextHolder
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

    fun resetFactory() {
        ContextHolder.context().sendBroadcast(Intent("android.intent.action.FACTORY_RESET").apply {
            addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
            putExtra("android.intent.extra.REASON", "MasterClearConfirm")
            putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", true)
            setPackage("android")
        })
    }
}