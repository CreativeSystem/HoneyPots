package br.com.creative.honeypots

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class AppController : Application(){
    override fun onCreate() {
        super.onCreate()
       // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}