package br.com.creative.honeypots.presentation.signin

import android.os.Bundle

import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemNavigationBar()
        setContentView(R.layout.activity_sign_in)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemNavigationBar(hasFocus);
    }


}