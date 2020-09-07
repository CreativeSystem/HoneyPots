package br.com.creative.honeypots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    }
}