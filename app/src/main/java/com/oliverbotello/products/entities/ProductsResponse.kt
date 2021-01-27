package com.oliverbotello.products.entities

data class ProductsResponse (
    val totalResults: Int,
    val page: Int,
    val items: List<Product>
)