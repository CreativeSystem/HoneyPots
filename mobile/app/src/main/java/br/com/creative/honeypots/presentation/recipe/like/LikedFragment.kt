package br.com.creative.honeypots.presentation.recipe.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.creative.honeypots.R
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import br.com.creative.honeypots.presentation.recipe.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_liked.*
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

        SearchAppBarView.regularTopAppBar.title = getString(R.string.liked_title)

        val navController = findNavController()

        view.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                navController.navigate(R.id.profileFragment)
            }

            override fun onSwipeRight() {
                navController.navigate(R.id.searchFragment)
            }
        })

        val likeViewModel by viewModels<LikeViewModel>()

        likeViewModel.recipesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { recipes ->
                with(searchList) {
                    layoutManager = GridLayoutManager(
                        this@LikedFragment.context,
                        3
                    )
                    setHasFixedSize(true)
                    adapter =
                        br.com.creative.honeypots.presentation.recipe.RecipeAdapter(recipes) { recipe ->
                            val intent =
                                br.com.creative.honeypots.presentation.recipe.detail.RecipeDetailActivity
                                    .getStartIntent(this@LikedFragment.requireContext(), recipe)
                            this@LikedFragment.startActivity(intent)
                        }
                }
            }
        })

        likeViewModel.getLikedRecipes()
    }

}
