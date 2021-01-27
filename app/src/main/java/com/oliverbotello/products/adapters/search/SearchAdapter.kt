package com.oliverbotello.products.adapters.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.oliverbotello.products.entities.Search

class SearchAdapter(private val onClick: (Search) -> Unit)  :
    ListAdapter<Search, SearchVH>(SearchDiffCallback)  {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchVH =
        SearchVH.newInstance(viewGroup, this.onClick)

    override fun onBindViewHolder(viewHolder: SearchVH, position: Int) {
        viewHolder.bind(this.getItem(position))
    }
}