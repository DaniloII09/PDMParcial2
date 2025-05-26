package com.parciales.parcial2.model

import com.parciales.parcial2.R

class ProductRepository {
    private val products = mutableListOf<Product>()

    init {
        // Datos dummy
        products.addAll(
            listOf(
                Product(
                    id = 1,
                    name = "Smartphone X",
                    category = "Electrónicos",
                    price = 599.99,
                    description = "Último modelo con cámara de 48MP",
                    image = "example.com"
                ),
                Product(
                    id = 2,
                    name = "Laptop Pro",
                    category = "Electrónicos",
                    price = 999.99,
                    description = "16GB RAM, 512GB SSD",
                    image = "example.com"
                ),
                // Agrega más productos según necesites
            )
        )
    }

    fun getAllProducts(): List<Product> = products

    fun searchProducts(query: String): List<Product> {
        return if (query.isBlank()) {
            products
        } else {
            products.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }
        }
    }

    fun getProductById(id: Int): Product? = products.find { it.id == id }

    fun updateProduct(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            products[index] = product
        }
    }

    fun getCartProducts(): List<Product> = products.filter { it.addedToCart }
}