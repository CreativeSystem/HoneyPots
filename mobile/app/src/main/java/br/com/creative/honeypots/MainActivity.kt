package br.com.creative.honeypots

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.presentation.BaseActivity
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

        for (i in 1..100) {
            recipes.add(Recipe("Item #$i", R.drawable.image_12))
        }

        with(recipeListView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = RecipeFeedAdapter(recipes)
        }

        bottomNavigationView.bottomNavigation.setupWithNavController(navController)

        bottomNavigationView.floatingButton.setOnClickListener {
            Toast.makeText(this, "NEW RECIPE", Toast.LENGTH_SHORT).show()
        }
    }
}