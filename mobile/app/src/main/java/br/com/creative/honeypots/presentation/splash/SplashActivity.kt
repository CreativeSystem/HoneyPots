package br.com.creative.honeypots.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import br.com.creative.honeypots.presentation.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*

class SplashActivity : BaseActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        activityScope.launch {
            delay(1500)
            var intent = Intent(this@SplashActivity, SignInActivity::class.java)
            var options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SplashActivity,
                logoImg,
                "logoTransition"
            )

            startActivity(intent, options.toBundle())
        }

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}