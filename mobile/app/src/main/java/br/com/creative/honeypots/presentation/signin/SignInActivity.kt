package br.com.creative.honeypots.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import br.com.creative.honeypots.MainActivity
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.view.*


class SignInActivity : BaseActivity() {
    private val facebookCallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemNavigationBar()
        setContentView(R.layout.activity_sign_in)

        initGoogleSignIn()
        initFacebookSignIn()

    }

    override fun onStart() {
        super.onStart()

        facebookContainer.isEnabled = true
        btnGoogleSignIn.isEnabled = true

        getGoogleSignInClient()?.signOut()
        Profile.getCurrentProfile()
        //LoginManager.getInstance().logOut()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemNavigationBar(hasFocus)
    }

    private fun onSignInSuccess(){
        val intent = Intent(this@SignInActivity, MainActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@SignInActivity,
            appLogo,
            "logoTransition"
        )

        startActivity(intent, options.toBundle())
    }

    private fun initGoogleSignIn() {
        val googleSignInClient = getGoogleSignInClient()

        btnGoogleSignIn.setOnClickListener { googleSignButton ->
            googleSignButton.isEnabled = false
            startActivityForResult(googleSignInClient?.signInIntent, Companion.RC_GOOGLE_SIGN_IN)
        }
    }

    private fun getGoogleSignInClient(): GoogleSignInClient? {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.google_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, googleSignInOptions)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }else{
            facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            completedTask.getResult(ApiException::class.java)

            //onSignInSuccess()

        } catch (e: ApiException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun initFacebookSignIn(){
        facebookContainer.setOnClickListener { button ->
            button.isEnabled = false
            button.btnFacebookLogin.callOnClick()
        }
        val facebookCallbackManager = CallbackManager.Factory.create()
        btnFacebookLogin.setPermissions(resources.getStringArray(R.array.facebook_permissions).toList())

        btnFacebookLogin.registerCallback(
            facebookCallbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val profile: Profile = Profile.getCurrentProfile()
                    Toast.makeText(
                        this@SignInActivity,
                        loginResult?.accessToken?.token,
                        Toast.LENGTH_LONG
                    ).show()
                    facebookContainer.isEnabled = true
                }

                override fun onCancel() {
                    Toast.makeText(this@SignInActivity, "Cancelado", Toast.LENGTH_LONG).show()
                    facebookContainer.isEnabled = true
                }

                override fun onError(exception: FacebookException) {
                    Toast.makeText(this@SignInActivity, exception.message, Toast.LENGTH_LONG).show()
                    facebookContainer.isEnabled = true
                }
            })

    }

    override fun onBackPressed() {}

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 1
    }
}