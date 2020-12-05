package br.com.creative.honeypots.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val token: String,
    val type: String,
    val expiresAt: String
)