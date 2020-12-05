package br.com.creative.honeypots.data.api

import br.com.creative.honeypots.data.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {
    @GET("recipe")
    fun listRecipes(@Query("search") search: String): Call<List<RecipeResponse>>

    @GET("me/recipe")
    fun listMyRecipes(): Call<List<RecipeResponse>>

    @GET("recipe/liked")
    fun listLikedRecipes(): Call<List<RecipeResponse>>

    @GET("recipe/visualized")
    fun listVisualizedRecipes(): Call<List<RecipeResponse>>

    @POST("recipe/{id}/visualize")
    fun visualizeRecipe(@Path("id") id: String): Call<Void>

    @POST("recipe/{id}/toggle-like")
    fun toggleLikeRecipe(@Path("id") id: String): Call<Void>
}