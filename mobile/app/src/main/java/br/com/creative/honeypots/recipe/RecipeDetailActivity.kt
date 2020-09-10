package br.com.creative.honeypots.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.view.size
import br.com.creative.honeypots.R
import br.com.creative.honeypots.Recipe
import br.com.creative.honeypots.presentation.BaseActivity
import kotlinx.android.synthetic.main.recipe_detail.*

class RecipeDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_detail)

        val recipe = intent.getSerializableExtra(RECIPE) as Recipe

        recipe?.let {
            val adapter = RecipeListAdapter(this@RecipeDetailActivity, R.layout.honey_simple_list_item_1, recipe.ingredients)
            val listSize = recipe.ingredients.size * 150


            txtRecipeName.text = recipe.name
            ingredientList.adapter = adapter
            ingredientList.layoutParams.height = listSize
            imageItem.setImageResource(recipe.image)
            itemDescription.text = recipe.description
        }
    }

    companion object {
        private const val RECIPE = "EXTRA_RECIPE"

        fun getStartIntent(context: Context, recipe: Recipe): Intent {
            return Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra(RECIPE, recipe)
            }
        }
    }
}