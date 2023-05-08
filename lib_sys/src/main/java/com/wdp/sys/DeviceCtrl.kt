package com.wdp.sys

import android.content.Intent

/**
 * 作者：王东平
 * 功能：
 * 说明：
 *    1.<uses-permission android:name="android.permission.MASTER_CLEAR"/> 需要系统权限
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