package com.wdp.sys.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.wdp.sys.SysUtils
import com.wdp.sys.SettingsUtil
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MainActivity"
    }

    private val scope = CoroutineScope(CoroutineName(TAG))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_shutdown).setOnClickListener(this)
        findViewById<Button>(R.id.btn_reboot).setOnClickListener(this)
        findViewById<Button>(R.id.btn_reset_factory).setOnClickListener(this)
        findViewById<Button>(R.id.btn_write_prop_wdp_1).setOnClickListener(this)
        findViewById<Button>(R.id.btn_write_settings_wdp_1).setOnClickListener(this)

        SysUtils.deviceInfo.getSerialNumber()?.let {
            findViewById<TextView>(R.id.txt_sn).text = "sn:$it"
        }
        SysUtils.deviceInfo.getVersion()?.let {
            findViewById<TextView>(R.id.txt_rom_version).text = "rom version:$it"
        }
        readProp()

        readSettings()

        SysUtils.settings.system.registerOnDataChangeListener(object :
            SettingsUtil.OnDataChangeListener {
            override fun onDataChanged(editor: SettingsUtil.Editor, key: String) {
                Log.d(TAG, "onDataChanged: $key")
            }

            override fun onDataChanged(key: String, newValue: String?) {
                Log.d(TAG, "onDataChanged: $key --> $newValue")
            }
        })
    }

    private fun readProp() {
        SysUtils.systemProp.getString("prop_wdp_1", null).let {
            findViewById<TextView>(R.id.txt_read_prop_wdp_1).text = "read prop:prop_wdp_1:$it"
        }
    }

    private fun readSettings() {
        SysUtils.settings.system.getString("settings_wdp_1", null).let {
            findViewById<TextView>(R.id.txt_read_settings_wdp_1).text =
                "read settings:settings_wdp_1:$it"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_shutdown -> {
                SysUtils.deviceCtrl.shutdown()
            }

            R.id.btn_reboot -> {
                SysUtils.deviceCtrl.reboot()
            }

            R.id.btn_reset_factory -> {
                SysUtils.deviceCtrl.resetFactory()
            }

            R.id.btn_write_prop_wdp_1 -> {
                SysUtils.systemProp.set("prop_wdp_1", "NB")
                scope.launch(Dispatchers.Main) {
                    delay(800)
                    readProp()
                }
            }

            R.id.btn_write_settings_wdp_1 -> {
                SysUtils.settings.system.put("settings_wdp_1", "NB")
                scope.launch(Dispatchers.Main) {
                    delay(800)
                    readSettings()
                }
            }
        }
    }
}