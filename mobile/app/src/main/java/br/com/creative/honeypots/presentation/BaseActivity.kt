package br.com.creative.honeypots.presentation

import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun hideSystemNavigationBar() {
        hideSystemNavigationBar(true)
    }

    fun hideSystemNavigationBar(hasFocus: Boolean) {
        if (hasFocus) {
            val visibility: Int = if (Build.VERSION.SDK_INT >= 19) {
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            } else {
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            }
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or visibility
        }
    }
}