package br.com.creative.honeypots.presentation.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.creative.honeypots.BuildConfig
import br.com.creative.honeypots.R
import br.com.creative.honeypots.data.model.Recipe
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper
import kotlinx.android.synthetic.main.layout_item_list.view.*

class RecipeAdapter(
    private val items: List<Recipe>,
    private val onItemClickListener: ((recipe: Recipe)-> Unit)
): RecyclerView.Adapter<RecipeAdapter.RecipeFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): RecipeFeedViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_list, parent, false)
        return RecipeFeedViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: RecipeFeedViewHolder, position: Int) {
        viewHolder.bindView(items[position]);
    }

    override fun getItemCount() = items.size

    class RecipeFeedViewHolder(
        itemView: View,
        private val onItemClickListener: ((recipe: Recipe)-> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.itemName;
        private val imageView: ImageView = itemView.itemImage;

        fun bindView(recipe: Recipe) {
            name.text = recipe.content.name;

            val images = recipe.content.images
            if(images.isNotEmpty()){
                UrlImageViewHelper.setUrlDrawable(imageView,getImageUrl(images[0]))
            }

            itemView.setOnClickListener {
                onItemClickListener.invoke(recipe);
            }
        }

        private fun getImageUrl(image:String): String {
            return "${BuildConfig.BACKEND_URL}image/$image"
        }
    }
}