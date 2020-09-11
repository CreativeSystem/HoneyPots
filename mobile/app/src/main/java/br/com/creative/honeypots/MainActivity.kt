package br.com.creative.honeypots

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.creative.honeypots.presentation.BaseActivity
import br.com.creative.honeypots.presentation.CreateRecipeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            val intent = Intent(this, CreateRecipeActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialog)
            .setMessage(R.string.confirm_quit)
            .setPositiveButton(R.string.confirm_quit_yes) { _, _ -> quit() }
            .setNegativeButton(R.string.confirm_quit_no) { _, _ -> }
            .show()
    }
}