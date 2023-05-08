package com.wdp.sys

import android.content.ContentResolver
import android.database.ContentObserver
import android.net.Uri
import android.provider.Settings
import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 作者：王东平
 * 功能：
 * 说明：1.申请权限<uses-permission android:name="android.permission.WRITE_SETTINGS" />
 *      2.读取不需要系统权限，**写入需要系统权限**
 * 版本：1.0.1
 */
object SettingsUtil {

    private const val TAG: String = "SettingsUtil"

    val system: Editor by lazy { System() }

    val global: Editor by lazy { Global() }

    val secure: Editor by lazy { Secure() }

    interface Editor {
        fun put(key: String, value: Int): Boolean {
            return putInt(key, value)
        }

        fun put(key: String, value: Float): Boolean {
            return putFloat(key, value)
        }

        fun put(key: String, value: String?): Boolean {
            return putString(key, value)
        }

        fun put(key: String, value: Boolean): Boolean {
            return putBoolean(key, value)
        }

        fun putInt(key: String, value: Int): Boolean

        fun putFloat(key: String, value: Float): Boolean

        fun putString(key: String, value: String?): Boolean

        fun putBoolean(key: String, value: Boolean): Boolean {
            return putInt(key, if (value) 1 else 0)
        }

        fun getInt(key: String, defValue: Int): Int

        fun getInt(key: String): Int {
            return getInt(key, -1)
        }

        fun getFloat(key: String, defValue: Float): Float

        fun getFloat(key: String): Float {
            return getFloat(key, -1.0F)
        }

        fun getString(key: String, defValue: String? = null): String?

        fun getBoolean(key: String, defValue: Boolean): Boolean {
            return getInt(key, if (defValue) 1 else 0) == 1
        }

        fun getBoolean(key: String): Boolean {
            return getBoolean(key, false)
        }

        fun registerOnDataChangeListener(listener: OnDataChangeListener)

        fun unregisterOnDataChangeListener(listener: OnDataChangeListener)
    }

    interface OnDataChangeListener {
        fun onDataChanged(editor: Editor, key: String)
        fun onDataChanged(key: String, newValue: String?) {}
    }

    @Suppress("LeakingThis")
    abstract class SettingsEditor : Editor, ContentObserver(null) {
        private val onDataChangeListeners = CopyOnWriteArrayList<OnDataChangeListener>()
        protected val contentResolver: ContentResolver = ContextHolder.context().contentResolver
        override fun onChange(selfChange: Boolean, _uri: Uri?) {
            super.onChange(selfChange, _uri)
            _uri?.let { uri ->
                val key = uri.pathSegments[1]
                val value = getString(key, null)
                Log.d(TAG, "onChange: $uri --> $value")
                onDataChangeListeners.forEach {
                    it.onDataChanged(this, key)
                    it.onDataChanged(key, value)
                }
            }
        }

        init {
            contentResolver.registerContentObserver(getUri(), true, this)
        }

        protected abstract fun getUri(): Uri

        override fun registerOnDataChangeListener(listener: OnDataChangeListener) {
            if (!onDataChangeListeners.contains(listener)) {
                onDataChangeListeners.add(listener)
            }
        }

        override fun unregisterOnDataChangeListener(listener: OnDataChangeListener) {
            if (onDataChangeListeners.contains(listener)) {
                onDataChangeListeners.remove(listener)
            }
        }
    }

    internal class System : SettingsEditor() {
        override fun getUri(): Uri {
            return Settings.System.CONTENT_URI
        }

        override fun putInt(key: String, value: Int): Boolean {
            return Settings.System.putInt(contentResolver, key, value)
        }

        override fun putFloat(key: String, value: Float): Boolean {
            return Settings.System.putFloat(contentResolver, key, value)
        }

        override fun putString(key: String, value: String?): Boolean {
            return Settings.System.putString(contentResolver, key, value)
        }

        override fun getInt(key: String, defValue: Int): Int {
            return Settings.System.getInt(contentResolver, key, defValue)
        }

        override fun getFloat(key: String, defValue: Float): Float {
            return Settings.System.getFloat(contentResolver, key, defValue)
        }

        override fun getString(key: String, defValue: String?): String? {
            return Settings.System.getString(contentResolver, key) ?: defValue
        }
    }

    internal class Global : SettingsEditor() {
        override fun getUri(): Uri {
            return Settings.Global.CONTENT_URI
        }

        override fun putInt(key: String, value: Int): Boolean {
            return Settings.Global.putInt(contentResolver, key, value)
        }

        override fun putFloat(key: String, value: Float): Boolean {
            return Settings.Global.putFloat(contentResolver, key, value)
        }

        override fun putString(key: String, value: String?): Boolean {
            return Settings.Global.putString(contentResolver, key, value)
        }

        override fun getInt(key: String, defValue: Int): Int {
            return Settings.Global.getInt(contentResolver, key, defValue)
        }

        override fun getFloat(key: String, defValue: Float): Float {
            return Settings.Global.getFloat(contentResolver, key, defValue)
        }

        override fun getString(key: String, defValue: String?): String? {
            return Settings.Global.getString(contentResolver, key) ?: defValue
        }
    }

    internal class Secure : SettingsEditor() {
        override fun getUri(): Uri {
            return Settings.Secure.CONTENT_URI
        }

        override fun putInt(key: String, value: Int): Boolean {
            return Settings.Secure.putInt(contentResolver, key, value)
        }

        override fun putFloat(key: String, value: Float): Boolean {
            return Settings.Secure.putFloat(contentResolver, key, value)
        }

        override fun putString(key: String, value: String?): Boolean {
            return Settings.Secure.putString(contentResolver, key, value)
        }

        override fun getInt(key: String, defValue: Int): Int {
            return Settings.Secure.getInt(contentResolver, key, defValue)
        }

        override fun getFloat(key: String, defValue: Float): Float {
            return Settings.Secure.getFloat(contentResolver, key, defValue)
        }

        override fun getString(key: String, defValue: String?): String? {
            return Settings.Secure.getString(contentResolver, key) ?: defValue
        }
    }
}









