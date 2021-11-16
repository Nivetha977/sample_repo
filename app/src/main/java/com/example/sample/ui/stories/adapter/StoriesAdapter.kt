package com.example.sample.ui.stories.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.databinding.ItemStoriesBinding
import com.example.sample.ui.stories.model.StoriesData

/**
 *Created by Nivetha S on 15-11-2021.
 */
class StoriesAdapter(private val storiesList: List<StoriesData>) :
    RecyclerView.Adapter<StoriesAdapter.ViewHolder>(), Filterable {

    var storiesFilterList = ArrayList<StoriesData>()

    init {
        storiesFilterList = storiesList as ArrayList<StoriesData>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesAdapter.ViewHolder {
        val binding =
            ItemStoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesAdapter.ViewHolder, position: Int) {
        with(holder.binding) {

            textViewTitle.text = HtmlCompat.fromHtml(
                "<font color='blue'> <u>Title: </u> </font>" + " " + storiesList[position].title,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            textViewAuthor.text = HtmlCompat.fromHtml(
                "<font color='blue'> <u>Author Name: </u> </font>" + " " + storiesList[position].authorName,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    }

    override fun getItemCount(): Int {
        Log.e("Nive ", "getItemCount: " + storiesFilterList.size)
        return storiesFilterList.size
    }

    class ViewHolder(val binding: ItemStoriesBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    storiesFilterList = storiesList as ArrayList<StoriesData>
                } else {
                    val resultList = ArrayList<StoriesData>()
                    for (row in storiesList) {
                        if (row.title.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    storiesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = storiesFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                storiesFilterList = results?.values as ArrayList<StoriesData>
                notifyDataSetChanged()
            }
        }
    }
}