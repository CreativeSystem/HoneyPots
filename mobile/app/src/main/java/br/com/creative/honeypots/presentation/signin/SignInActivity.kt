package br.com.creative.honeypots.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import br.com.creative.honeypots.AppController
import br.com.creative.honeypots.presentation.recipe.MainActivity
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.BaseActivity
import com.facebook.*
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

    val signInViewModel by viewModels<SignInViewModel>()

    private var facebookCallbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        hideSystemNavigationBar()

        getGoogleSignInClient()?.signOut()
        LoginManager.getInstance().logOut()

        initGoogleSignIn()
        initFacebookSignIn()

        signInViewModel.isAuthenticatedLiveData.observe(this, Observer {

            if(!it) return@Observer

            var editor = AppController.appSettingsPrefs.edit()
            editor.putString(getString(R.string.sp_token_key),signInViewModel.tokenLiveData.value)
            editor.apply()

            AppController.token = signInViewModel.tokenLiveData.value!!

            onSignInSuccess()
        })

        signInViewModel.loginErrorLiveData.observe(this, Observer {
            if(it) Toast.makeText(this@SignInActivity,R.string.error_login,Toast.LENGTH_SHORT).show()
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemNavigationBar(hasFocus)
    }

    private fun onSignInSuccess() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }

    private fun initGoogleSignIn() {
        val googleSignInClient = getGoogleSignInClient()

        btnGoogleSignIn.setOnClickListener {
            startActivityForResult(googleSignInClient?.signInIntent, RC_GOOGLE_SIGN_IN)
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
        if(resultCode != RESULT_OK) return
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        } else {
            facebookCallbackManager?.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) = try {
        val googleUser =  completedTask.getResult(ApiException::class.java)

        googleUser?.let {
            signInViewModel.loginGoogle(it.serverAuthCode!!)
        }

    } catch (e: ApiException) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }

    private fun initFacebookSignIn() {
        facebookCallbackManager = CallbackManager.Factory.create()

        facebookContainer.setOnClickListener { button ->
            button.btnFacebookLogin.callOnClick()
        }
        btnFacebookLogin.setPermissions(
            resources.getStringArray(R.array.facebook_permissions).toList()
        )

        btnFacebookLogin.registerCallback(
            facebookCallbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    loginResult?.let {
                        signInViewModel.loginFacebook(it.accessToken.token)
                    }
                }

                override fun onCancel() {
                    Toast.makeText(this@SignInActivity, "Cancelado", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    Toast.makeText(this@SignInActivity, exception.message, Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun onBackPressed() {}

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 1
    }
}