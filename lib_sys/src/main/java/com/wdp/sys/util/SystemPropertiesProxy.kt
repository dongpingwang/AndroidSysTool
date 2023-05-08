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

    fun getString(key: String, def: String? = null): String? {
        return get(key, def)
    }

    fun getInt(key: String, def: Int): Int? {
        return get(key, def)
    }

    fun getLong(key: String, def: Long): Long? {
        return get(key, def)
    }

    fun getBoolean(key: String, def: Boolean): Boolean? {
        return get(key, def)
    }

    private inline fun <reified T> get(key: String, def: T?): T? {
        return kotlin.runCatching {
            val clazz = Class.forName("android.os.SystemProperties")
            val method = clazz.getDeclaredMethod("get", String::class.java, T::class.java)
            method.isAccessible = true
            method.invoke(clazz, key, def) as T
        }.also { it.exceptionOrNull()?.printStackTrace() }.getOrDefault(def)
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