package br.com.creative.honeypots.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.R

class RecipeRecyclerViewAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_ingredient, parent, false)

        return RecipeViewHolder(textView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindView(myDataset[position])
    }

    override fun getItemCount() = myDataset.size

    class RecipeViewHolder(val textView: View) : RecyclerView.ViewHolder(textView) {
        fun bindView(itemName: String) {
            (textView as TextView).text = itemName
        }
    }
}