package br.com.creative.honeypots.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val id: String,
    val content: RecipeContentResponse
)