package com.oliverbotello.products.adapters.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oliverbotello.products.R
import com.oliverbotello.products.entities.Product

class ProductVH(view: View, val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(viewGroup: ViewGroup, onClick: (Product) -> Unit): ProductVH =
            ProductVH(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_row_product, viewGroup, false),
                onClick
            )
    }

    private var currentProduct: Product? = null

    init {
        this.itemView.setOnClickListener {
            this.currentProduct?.let {
                this.onClick(it)
            }
        }
    }

    fun bind(product: Product) {
        this.currentProduct = product
        this.itemView.findViewById<AppCompatTextView>(R.id.txtVw_ProductTitle).text = product.title
        this.itemView.findViewById<AppCompatTextView>(R.id.txtVw_ProductPrice).text =
            "$${product.price}"
        val rating: RatingBar = this.itemView.findViewById<RatingBar>(R.id.rtngBr_ProductRating)
        rating.rating = product.rating

        Glide.with(this.itemView.context).load(product.image)
            .into(this.itemView.findViewById(R.id.imgVw_ProductImage))
    }
}