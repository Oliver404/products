package com.oliverbotello.products.adapters.product

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.oliverbotello.products.entities.Product

class ProductAdapter(private val onClick: (Product) -> Unit)  :
    ListAdapter<Product, ProductVH>(ProductDiffCallback)  {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductVH =
        ProductVH.newInstance(viewGroup, this.onClick)

    override fun onBindViewHolder(viewHolder: ProductVH, position: Int) {
        viewHolder.bind(this.getItem(position))
    }
}