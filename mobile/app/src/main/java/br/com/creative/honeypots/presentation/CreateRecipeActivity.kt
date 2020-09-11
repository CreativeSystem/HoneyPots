package br.com.creative.honeypots.presentation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_recipe.*
import kotlinx.android.synthetic.main.regular_app_bar.view.*

class CreateRecipeActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val myDataset = arrayOf<String>("teste","teste","teste")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        createRecipeTopBar.regularTopAppBar.title = getString(R.string.create_recipe_title)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecipeRecyclerViewAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.ingredientsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogCreateRecipe)
            .setTitle(R.string.are_you_sure)
            .setMessage(R.string.confirm_leave_recipe)
            .setPositiveButton(R.string.confirm_quit_yes) { _, _ -> super.onBackPressed() }
            .setNegativeButton(R.string.confirm_quit_no) { _, _ -> }
            .show()
    }
}