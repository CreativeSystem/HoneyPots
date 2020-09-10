package br.com.creative.honeypots

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.presentation.OnSwipeTouchListener
import br.com.creative.honeypots.recipe.RecipeDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.feed_app_bar.view.*
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

        feedAppBarView.feedLogo.setOnClickListener {
            Toast.makeText(context, "teste5", Toast.LENGTH_SHORT).show()
        }

        feedAppBarView.feedTopAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.savory -> {
                    Toast.makeText(context, "teste1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.sweet -> {

                    Toast.makeText(context, "teste2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.drink -> {

                    Toast.makeText(context, "teste2", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        val recipes: ArrayList<Recipe> = ArrayList()

        val l = ArrayList<String>()
        l.add(0,"Ingredient 1")
        l.add(1,"Ingredient 2")
        l.add(2,"Ingredient 3")
        for (i in 1..20) {
            recipes.add(Recipe("Item #$i", l, R.drawable.image_12, "Description"))
        }

        with(recipeListView) {
            layoutManager =
                LinearLayoutManager(this@FeedFragment.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = RecipeFeedAdapter(recipes) { recipe ->
                val intent = RecipeDetailActivity
                    .getStartIntent(this@FeedFragment.requireContext(), recipe);
                this@FeedFragment.startActivity(intent);
            };
        }
    }
}