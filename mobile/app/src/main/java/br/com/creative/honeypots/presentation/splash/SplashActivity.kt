package br.com.creative.honeypots.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import br.com.creative.honeypots.MainActivity
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import br.com.creative.honeypots.presentation.signin.SignInActivity
import kotlinx.coroutines.*

class SplashActivity :BaseActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        activityScope.launch {
            delay(2000)
            var intent = Intent(this@SplashActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}