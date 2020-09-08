package br.com.creative.honeypots.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    fun hideSystemNavigationBar() {
        hideSystemNavigationBar(true)
    }

    fun hideSystemNavigationBar(hasFocus: Boolean) {
        if (hasFocus) {
            val visibility: Int =
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or visibility
        }
    }

    fun quit() {
        val intent = Intent(this, QuitActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        startActivity(intent)
        finish()
    }
}