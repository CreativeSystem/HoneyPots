package br.com.creative.honeypots

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import java.security.AccessController.getContext

class AppController : Application() {
    companion object{
        lateinit var appSettingsPrefs:SharedPreferences
        lateinit var token: String
    }
    override fun onCreate() {
        super.onCreate()

        appSettingsPrefs = getSharedPreferences("AppSettingsPrefs", MODE_PRIVATE)
        token = appSettingsPrefs.getString(getString(R.string.sp_token_key),"").toString()

        val isNightModeOn: Boolean = appSettingsPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}