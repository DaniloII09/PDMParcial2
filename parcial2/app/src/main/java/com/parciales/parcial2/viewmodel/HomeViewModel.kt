package com.parciales.parcial2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.parciales.parcial2.model.Product
import com.parciales.parcial2.model.ProductRepository

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _products = mutableStateOf(repository.getAllProducts())
    val products: State<List<Product>> = _products

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _products.value = repository.searchProducts(text)
    }

    fun addToCart(product: Product) {
        val updatedProduct = product.copy(addedToCart = true)
        repository.updateProduct(updatedProduct)
        _products.value = repository.searchProducts(_searchText.value)
    }

    fun removeFromCart(product: Product) {
        val updatedProduct = product.copy(addedToCart = false)
        repository.updateProduct(updatedProduct)
        _products.value = repository.searchProducts(_searchText.value)
    }
}