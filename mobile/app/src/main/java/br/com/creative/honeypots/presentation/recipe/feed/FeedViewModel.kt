package br.com.creative.honeypots.presentation.recipe.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.creative.honeypots.data.api.ApiService
import br.com.creative.honeypots.data.model.Recipe
import br.com.creative.honeypots.data.model.RecipeContent
import br.com.creative.honeypots.data.response.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel : ViewModel() {
    val likedRecipesLiveData = MutableLiveData<List<Recipe>>()
    val visualizedRecipesLiveData = MutableLiveData<List<Recipe>>()
    val myRecipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesError = MutableLiveData<Boolean>(false)

    fun getLikedRecipes() {
        ApiService.recipeService.listLikedRecipes()
            .enqueue(object : Callback<List<RecipeResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeResponse>>,
                    response: Response<List<RecipeResponse>>
                ) {
                    val recipes = mutableListOf<Recipe>()

                    response.body()?.forEach { recipe ->
                        val content = recipe.content
                        recipes.add(
                            Recipe(
                                recipe.id,
                                RecipeContent(
                                    content.name,
                                    content.durationInMinutes,
                                    content.images,
                                    content.description,
                                    content.steps
                                )
                            )
                        )
                    }

                    likedRecipesLiveData.value = recipes
                    recipesError.value = false
                }

                override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                    recipesError.value = true
                }
            })
    }

    fun getVisualizedRecipes() {
        ApiService.recipeService.listVisualizedRecipes()
            .enqueue(object : Callback<List<RecipeResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeResponse>>,
                    response: Response<List<RecipeResponse>>
                ) {
                    response.body()?.let { recipes ->
                        visualizedRecipesLiveData.value = convertRecipes(recipes)
                        recipesError.value = false
                    }
                }

                override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                    recipesError.value = true
                }
            })
    }

    fun getMyRecipes() {
        ApiService.recipeService.listMyRecipes()
            .enqueue(object : Callback<List<RecipeResponse>> {
                override fun onResponse(
                    call: Call<List<RecipeResponse>>,
                    response: Response<List<RecipeResponse>>
                ) {
                    response.body()?.let { recipes ->
                        myRecipesLiveData.value = convertRecipes(recipes)
                        recipesError.value = false
                    }
                }

                override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                    recipesError.value = true
                }
            })
    }

    fun getAll(){
        getLikedRecipes()
        getMyRecipes()
        getVisualizedRecipes()
    }
    private fun convertRecipes( recipesResponse: List<RecipeResponse>): MutableList<Recipe>{
        val recipes = mutableListOf<Recipe>()

        recipesResponse.forEach { recipe ->
            val content = recipe.content
            recipes.add(
                Recipe(
                    recipe.id,
                    RecipeContent(
                        content.name,
                        content.durationInMinutes,
                        content.images,
                        content.description,
                        content.steps
                    )
                )
            )
        }

        return recipes
    }
}