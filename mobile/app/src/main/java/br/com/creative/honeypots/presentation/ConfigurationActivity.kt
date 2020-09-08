package br.com.creative.honeypots.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import br.com.creative.honeypots.R
import br.com.creative.honeypots.SearchFragment
import kotlinx.android.synthetic.main.activity_configuration.*
import kotlinx.android.synthetic.main.regular_app_bar.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        arrowBack.setOnClickListener {
            onBackPressed()
        }

        val appSettingsPrefs: SharedPreferences = getSharedPreferences("AppSettingsPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingsPrefs.edit()
        val isNightModeOn: Boolean = appSettingsPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            lightDarkModeSwitch.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            lightDarkModeSwitch.isChecked = false
        }

        lightDarkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true).apply()
                return@setOnCheckedChangeListener
            }
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefsEdit.putBoolean("NightMode", false).apply()
        }

        configurationAppBarView.regularTopAppBar.title = getString(R.string.configuration_title)
    }
}