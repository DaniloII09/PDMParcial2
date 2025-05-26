package com.parciales.parcial2.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val description: String,
    val image: String,
    var addedToCart: Boolean = false
)