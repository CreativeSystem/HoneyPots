package br.com.creative.honeypots.data.model

data class RecipeContent(
    val name: String,
    val durationInMinutes: Int,
    val images : List<String>,
    val description: String,
    val steps: List<Map<String,Object>>
)