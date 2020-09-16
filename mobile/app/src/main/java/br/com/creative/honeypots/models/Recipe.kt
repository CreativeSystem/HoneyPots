package br.com.creative.honeypots

import java.io.Serializable

data class Recipe (
    val name: String,
    val ingredients: ArrayList<String>,
    val image: Int,
    val description: String
): Serializable