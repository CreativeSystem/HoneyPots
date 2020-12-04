package br.com.creative.honeypots.recipe

import android.os.Bundle
import br.com.creative.honeypots.R
import br.com.creative.honeypots.Recipe
import br.com.creative.honeypots.presentation.BaseActivity
import kotlinx.android.synthetic.main.recipe_detail.*

class RecipeCreateActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_create)
    }

}