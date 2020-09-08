package br.com.creative.honeypots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SearchAppBarView.regularTopAppBar.title = getString(R.string.search_title)

        val navController = findNavController()

        view.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                navController.navigate(R.id.likedFragment)
            }

            override fun onSwipeRight() {
                navController.navigate(R.id.feedFragment)
            }
        })
    }
}