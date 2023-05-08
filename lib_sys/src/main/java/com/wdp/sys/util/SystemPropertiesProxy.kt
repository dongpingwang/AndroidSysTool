package com.wdp.sys.util

import android.annotation.SuppressLint

/**
 * 作者：王东平
 * 功能：
 * 说明：
 * 版本：1.0.0
 */
@SuppressLint("PrivateApi", "DiscouragedPrivateApi")
object SystemPropertiesProxy {

    fun getString(key: String, defValue: String? = null): String? {
        return get(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int? {
        return get(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long? {
        return get(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean? {
        return get(key, defValue)
    }

    private inline fun <reified T> get(key: String, defValue: T?): T? {
        return kotlin.runCatching {
            val clazz = Class.forName("android.os.SystemProperties")
            val method = clazz.getDeclaredMethod("get", String::class.java, T::class.java)
            method.isAccessible = true
            method.invoke(clazz, key, defValue) as T
        }.also { it.exceptionOrNull()?.printStackTrace() }.getOrDefault(defValue)
    }


    fun set(key: String, value: String?) {
        kotlin.runCatching {
            val clazz = Class.forName("android.os.SystemProperties")
            val method = clazz.getDeclaredMethod("set", String::class.java, String::class.java)
            method.isAccessible = true
            method.invoke(clazz, key, value)
        }.also { it.exceptionOrNull()?.printStackTrace() }
    }
}