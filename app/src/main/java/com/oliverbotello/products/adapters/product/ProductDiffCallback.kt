package com.oliverbotello.products.adapters.product

import androidx.recyclerview.widget.DiffUtil
import com.oliverbotello.products.entities.Product

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id
}