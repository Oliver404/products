package com.oliverbotello.products.entities

data class Product (
    val id: String,
    val rating: Float,
    val price: Double,
    val image: String,
    val title: String
)