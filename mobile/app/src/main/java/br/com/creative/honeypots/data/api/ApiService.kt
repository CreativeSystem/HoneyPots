package br.com.creative.honeypots.data.api

import android.content.Context
import br.com.creative.honeypots.AppController
import br.com.creative.honeypots.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient() : OkHttpClient {
        val authInterceptor = Interceptor{ chain ->

            val requestBuilder = chain.request().newBuilder()

            if(AppController.token.isNotBlank()){
                requestBuilder.addHeader("Authorization","Bearer " + AppController.token)
            }

            return@Interceptor chain.proceed(requestBuilder.build())
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(authInterceptor)
            .build();
    }

    val loginService: LoginService = initRetrofit().create(LoginService::class.java)
    val recipeService: RecipeService = initRetrofit().create(RecipeService::class.java)
}