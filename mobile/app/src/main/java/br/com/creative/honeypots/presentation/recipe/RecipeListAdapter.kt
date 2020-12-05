package br.com.creative.honeypots.presentation.recipe

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.com.creative.honeypots.R

class RecipeListAdapter(context: Context, var anySource: Int, var items: ArrayList<String>): ArrayAdapter<String>(context, anySource, items) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(anySource, null)

        val itemText: TextView = view.findViewById(R.id.txtItem)

        val item = items[position]
        itemText.text = item

        return view
    }
}