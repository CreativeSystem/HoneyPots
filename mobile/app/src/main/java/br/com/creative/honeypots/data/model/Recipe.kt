package br.com.creative.honeypots.data.model

import java.io.Serializable

data class Recipe (
    val id: String,
    val content: RecipeContent
): Serializable