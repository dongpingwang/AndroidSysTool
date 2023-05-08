package com.wdp.sys

import android.annotation.SuppressLint
import android.content.Context
import java.lang.IllegalStateException

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
@SuppressLint("StaticFieldLeak")
object ContextHolder {

    private lateinit var mContext: Context

    fun setContext(context: Context) {
        mContext = context
    }

    fun getContext(): Context {
        checkInit()
        return mContext
    }

    fun context() = getContext()


    private fun checkInit() {
        if (!this::mContext.isInitialized) {
            throw IllegalStateException("must init please，do you call **SysUtils.init**?")
        }
    }
}
