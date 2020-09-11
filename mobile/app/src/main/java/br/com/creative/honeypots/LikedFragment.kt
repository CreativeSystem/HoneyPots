package br.com.creative.honeypots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_liked.*
import kotlinx.android.synthetic.main.navigation_menu.*
import kotlinx.android.synthetic.main.navigation_menu.view.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class LikedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        likedAppBarView.regularTopAppBar.title = getString(R.string.liked_title)

        val navController = findNavController()

        view.setOnTouchListener(object : OnSwipeTouchListener(view.context) {
            override fun onSwipeLeft() {
                navController.navigate(R.id.profileFragment)
            }

            override fun onSwipeRight() {
                navController.navigate(R.id.searchFragment)
            }
        })
    }

}
