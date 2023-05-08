package com.wdp.sys.demo

import android.app.Application
import android.util.Log
import com.wdp.sys.SysUtils

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
class DemoApplication : Application() {
    companion object {
        const val TAG = "DemoApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        SysUtils.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        SysUtils.deInit()
    }
}