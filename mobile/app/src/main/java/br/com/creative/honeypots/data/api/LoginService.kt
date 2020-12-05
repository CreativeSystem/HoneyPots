package br.com.creative.honeypots.data.api

import br.com.creative.honeypots.data.response.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("login")
    fun login(
        @Query("social") social: String,
        @Query("accessToken") accessToken: String
    ): Call<LoginResponse>
}