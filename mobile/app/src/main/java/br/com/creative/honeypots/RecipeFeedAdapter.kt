package br.com.creative.honeypots

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_list.view.*

class RecipeFeedAdapter(private val items: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeFeedAdapter.RecipeFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): RecipeFeedViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_list, parent, false)
        return RecipeFeedViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecipeFeedViewHolder, position: Int) {
        viewHolder.bindView(items[position]);
    }

    override fun getItemCount() = items.size

    inner class RecipeFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.itemName;
        private val image: ImageView = itemView.itemImage;

        init {
            itemView.setOnClickListener {view: View ->
                val position: Int = adapterPosition;
                Toast.makeText(itemView.context, "Recipe #${position + 1}", Toast.LENGTH_SHORT).show();
            }
        }

        fun bindView(recipe: Recipe) {
            name.text = recipe.name;
            image.setImageResource(recipe.image);
        }
    }
}