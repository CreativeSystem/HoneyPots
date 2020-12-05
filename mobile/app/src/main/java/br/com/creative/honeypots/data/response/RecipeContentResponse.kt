package br.com.creative.honeypots.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeContentResponse(
    val name: String,
    val durationInMinutes: Int,
    val images : List<String>,
    val description: String,
    val steps: List<Map<String,Object>>

)