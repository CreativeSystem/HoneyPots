package br.com.creative.honeypots.presentation.recipe.search

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
import br.com.creative.honeypots.presentation.recipe.RecipeAdapter
import br.com.creative.honeypots.presentation.recipe.detail.RecipeDetailActivity
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*
import kotlinx.android.synthetic.main.search_input.view.*
import java.util.concurrent.TimeUnit

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

        val searchViewModel by viewModels<SearchViewModel>()

        searchViewModel.recipesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let{recipes ->
                with(searchList){
                    layoutManager = GridLayoutManager(this@SearchFragment.context, 3)
                    setHasFixedSize(true)
                    adapter = RecipeAdapter(recipes){ recipe ->
                        val intent = RecipeDetailActivity
                            .getStartIntent(this@SearchFragment.requireContext(), recipe)
                        this@SearchFragment.startActivity(intent)
                    }
                }
            }
        })


        RxTextView.textChanges(SearchInputView.searchInput)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map { text ->
                text.toString()
            }
            .subscribe { search ->
                if(search.isNotEmpty()) searchViewModel.searchRecipe(search)
            }

    }
}