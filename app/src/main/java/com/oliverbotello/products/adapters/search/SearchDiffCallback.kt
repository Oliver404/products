package com.oliverbotello.products.adapters.search

import androidx.recyclerview.widget.DiffUtil
import com.oliverbotello.products.entities.Search

object SearchDiffCallback : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean =
        oldItem.value.equals(newItem.value)
}