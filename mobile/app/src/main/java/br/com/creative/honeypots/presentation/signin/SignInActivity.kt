package br.com.creative.honeypots.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import br.com.creative.honeypots.MainActivity
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : BaseActivity() {

    private val RC_GOOGLE_SIGN_IN = 10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemNavigationBar()
        setContentView(R.layout.activity_sign_in)

        initGoogleSignIn()

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

    private fun initGoogleSignIn(){
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        btnGoogleSignIn.setOnClickListener { googleSignButton->
            googleSignButton.isEnabled = false
            startActivityForResult(googleSignInClient.signInIntent, RC_GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_GOOGLE_SIGN_IN){
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            Toast.makeText(this,account?.idToken,Toast.LENGTH_SHORT)

        } catch (e: ApiException) {
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT)
        }
    }

}