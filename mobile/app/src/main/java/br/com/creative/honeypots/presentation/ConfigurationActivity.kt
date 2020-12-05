package br.com.creative.honeypots.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import br.com.creative.honeypots.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_configuration.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class ConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        deleteAccount.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogDeleteAccount)
                .setTitle(R.string.title_delete_account)
                .setMessage(R.string.message_delete_account)
                .setView(R.layout.delete_text_input)
                .setPositiveButton(R.string.confirm_delete_account) { _, _ -> }
                .setNegativeButton(R.string.cancel_delete_account) { _, _ -> }
                .show()
        }

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