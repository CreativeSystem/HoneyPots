package br.com.creative.honeypots.presentation.signin

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import br.com.creative.honeypots.MainActivity
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemNavigationBar()
        setContentView(R.layout.activity_sign_in)

        facebookContainer.setOnClickListener {
            var intent = Intent(this@SignInActivity, MainActivity::class.java)
            var options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SignInActivity,
                appLogo,
                "logoTransition"
            )

            startActivity(intent, options.toBundle())
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemNavigationBar(hasFocus)
    }


}