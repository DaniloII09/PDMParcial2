package com.parciales.parcial2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.parciales.parcial2.model.Product
import com.parciales.parcial2.model.ProductRepository

class CartViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _cartProducts = mutableStateOf(repository.getCartProducts())
    val cartProducts: State<List<Product>> = _cartProducts

    fun removeFromCart(product: Product) {
        val updatedProduct = product.copy(addedToCart = false)
        repository.updateProduct(updatedProduct)
        _cartProducts.value = repository.getCartProducts()
    }
}