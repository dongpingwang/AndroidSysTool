package com.wdp.sys

import android.content.Context
import android.util.Log

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
object SysUtils {

    private const val TAG = "SysUtils"
    private const val VERSION = "1.0.0"

    val deviceCtrl by lazy { DeviceCtrl() }

    val deviceInfo by lazy { DeviceInfo() }

    val systemProp by lazy { SystemPropertiesProxy }

    val settings by lazy { SettingsUtil }

    fun init(ctx: Context) {
        ContextHolder.setContext(ctx)
        Log.d(TAG, "init: $VERSION")
    }

    fun deInit() {
        Log.d(TAG, "deInit")
    }

}