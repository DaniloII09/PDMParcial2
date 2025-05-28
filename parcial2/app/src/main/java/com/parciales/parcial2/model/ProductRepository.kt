package com.parciales.parcial2.model

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
                    image = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-13-finish-select-202207-6-1inch-blue?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1656712882664"
                ),
                Product(
                    id = 2,
                    name = "Laptop Pro",
                    category = "Electrónicos",
                    price = 999.99,
                    description = "16GB RAM, 512GB SSD",
                    image = "https://www.apple.com/v/macbook-pro-14-and-16/b/images/overview/hero/hero_intro_endframe__e6khcva4hkeq_large.jpg"
                ),
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