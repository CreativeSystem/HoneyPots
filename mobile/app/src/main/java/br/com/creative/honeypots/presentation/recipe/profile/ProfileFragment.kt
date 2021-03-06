package br.com.creative.honeypots.presentation.recipe.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.creative.honeypots.AppController
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.ConfigurationActivity
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import br.com.creative.honeypots.presentation.signin.SignInActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAppBarView.regularTopAppBar.title = getString(R.string.profile_title)

        val navController = findNavController()

        view.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeRight() {
                navController.navigate(R.id.likedFragment)
            }
        })

        appConfiguration.setOnClickListener {
            val intent = Intent(context, ConfigurationActivity::class.java)

            startActivity(intent)
        }
        logout.setOnClickListener {
            var editor = AppController.appSettingsPrefs.edit()
            editor.remove(getString(R.string.sp_token_key))
            editor.apply()
            AppController.token = ""

            val intent = Intent(context, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
