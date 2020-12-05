package br.com.creative.honeypots.presentation.recipe.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.R
import br.com.creative.honeypots.data.model.Recipe
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import br.com.creative.honeypots.presentation.recipe.RecipeAdapter
import br.com.creative.honeypots.presentation.recipe.detail.RecipeDetailActivity
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                navController.navigate(R.id.searchFragment)
            }
        })

        val feedViewModel by viewModels<FeedViewModel>()
        feedViewModel.myRecipesLiveData.observe(viewLifecycleOwner, Observer {recipes ->
            with(myRecipeListView) {
                layoutManager =
                    LinearLayoutManager(this@FeedFragment.context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = RecipeAdapter(recipes) { recipe ->
                    val intent = RecipeDetailActivity
                        .getStartIntent(this@FeedFragment.requireContext(), recipe)
                    this@FeedFragment.startActivity(intent)
                }
            }
        })

        feedViewModel.likedRecipesLiveData.observe(viewLifecycleOwner, Observer {recipes ->
            with(likedListView) {
                layoutManager =
                    LinearLayoutManager(this@FeedFragment.context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = RecipeAdapter(recipes) { recipe ->
                    val intent = RecipeDetailActivity
                        .getStartIntent(this@FeedFragment.requireContext(), recipe)
                    this@FeedFragment.startActivity(intent)
                }
            }
        })

        feedViewModel.visualizedRecipesLiveData.observe(viewLifecycleOwner, Observer {recipes ->
            with(visualizedListView) {
                layoutManager =
                    LinearLayoutManager(this@FeedFragment.context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = RecipeAdapter(recipes) { recipe ->
                    val intent = RecipeDetailActivity
                        .getStartIntent(this@FeedFragment.requireContext(), recipe)
                    this@FeedFragment.startActivity(intent)
                }
            }
        })


        feedViewModel.getAll()
    }
}