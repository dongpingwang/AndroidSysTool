package com.wdp.sys.demo

import android.app.Application
import com.wdp.sys.SysUtils

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SysUtils.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        SysUtils.deInit()
    }
}