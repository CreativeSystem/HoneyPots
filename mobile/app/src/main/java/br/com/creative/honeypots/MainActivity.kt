package br.com.creative.honeypots

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.presentation.BaseActivity
import br.com.creative.honeypots.recipe.RecipeDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_menu.view.*

class MainActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        val recipes: ArrayList<Recipe> = ArrayList()

        val l = ArrayList<String>()
        l.add(0,"Ingredient 1")
        l.add(1,"Ingredient 2")
        l.add(2,"Ingredient 3")
        for (i in 1..20) {
            recipes.add(Recipe("Item #$i", l, R.drawable.image_12, "Description"))
        }

        with(recipeListView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = RecipeFeedAdapter(recipes) { recipe ->
                val intent = RecipeDetailActivity
                    .getStartIntent(this@MainActivity, recipe);
                this@MainActivity.startActivity(intent);
            };
        }

        bottomNavigationView.bottomNavigation.setupWithNavController(navController)

        bottomNavigationView.floatingButton.setOnClickListener {
            Toast.makeText(this, "NEW RECIPE", Toast.LENGTH_SHORT).show()
        }
    }
}