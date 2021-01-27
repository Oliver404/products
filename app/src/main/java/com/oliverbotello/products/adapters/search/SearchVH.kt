package com.oliverbotello.products.adapters.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliverbotello.products.R
import com.oliverbotello.products.entities.Search

class SearchVH(view: View, val onClick: (Search) -> Unit) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(viewGroup: ViewGroup, onClick: (Search) -> Unit): SearchVH =
            SearchVH(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_row_search, viewGroup, false),
                onClick
            )
    }

    private var currentSearch: Search? = null

    init {
        this.itemView.setOnClickListener {
            this.currentSearch?.let {
                this.onClick(it)
            }
        }
    }

    fun bind(search: Search) {
        this.currentSearch = search
        this.itemView.findViewById<TextView>(R.id.txtVw_Search).text = search.value
    }
}