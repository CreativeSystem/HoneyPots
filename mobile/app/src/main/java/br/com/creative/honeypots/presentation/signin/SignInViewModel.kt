package br.com.creative.honeypots.presentation.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.creative.honeypots.AppController
import br.com.creative.honeypots.data.api.ApiService
import br.com.creative.honeypots.data.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel() : ViewModel(){
    val tokenLiveData: MutableLiveData<String> = MutableLiveData(AppController.token)
    val isAuthenticatedLiveData: MutableLiveData<Boolean> = MutableLiveData(AppController.token.isNotEmpty())
    val loginErrorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    private val SOCIAL_GOOGLE = "google"
    private val SOCIAL_FACEBOOK = "facebook"

    fun loginGoogle(accessToken:String){
        login(SOCIAL_GOOGLE,accessToken)
    }

    fun loginFacebook(accessToken:String){
        login(SOCIAL_FACEBOOK,accessToken)
    }

    private fun login(socialType :String,accessToken:String ){
        ApiService.loginService.login(socialType,accessToken)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { response ->
                            tokenLiveData.value = response.token
                            isAuthenticatedLiveData.value = true
                            if (loginErrorLiveData.value!!) {
                                loginErrorLiveData.value = false
                            }
                        }
                    } else {
                        if (!isAuthenticatedLiveData.value!!) {
                            isAuthenticatedLiveData.value = false
                        }
                        loginErrorLiveData.value = true
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    if (!isAuthenticatedLiveData.value!!) {
                        isAuthenticatedLiveData.value = false
                    }
                    Log.e("Login", "onFailure: ",t )
                    loginErrorLiveData.value = true
                }
            })
    }
}