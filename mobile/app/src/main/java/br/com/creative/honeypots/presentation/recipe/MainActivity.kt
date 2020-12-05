package br.com.creative.honeypots.presentation.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.creative.honeypots.R
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

        bottomNavigationView.bottomNavigation.setupWithNavController(navController)

        bottomNavigationView.floatingButton.setOnClickListener {
            Toast.makeText(this, "NEW RECIPE", Toast.LENGTH_SHORT).show()
        }
    }
}