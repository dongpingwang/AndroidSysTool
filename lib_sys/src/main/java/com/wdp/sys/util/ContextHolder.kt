package com.wdp.sys.util

import android.annotation.SuppressLint
import android.content.Context

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
        return mContext
    }

    fun context() = getContext()
}
