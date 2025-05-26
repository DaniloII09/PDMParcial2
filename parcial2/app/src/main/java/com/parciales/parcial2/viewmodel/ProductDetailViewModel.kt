package com.parciales.parcial2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.parciales.parcial2.model.Product
import com.parciales.parcial2.model.ProductRepository

class ProductDetailViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    fun loadProduct(productId: Int) {
        _product.value = repository.getProductById(productId)
    }

    fun toggleCartStatus() {
        _product.value?.let { currentProduct ->
            val updatedProduct = currentProduct.copy(addedToCart = !currentProduct.addedToCart)
            repository.updateProduct(updatedProduct)
            _product.value = updatedProduct
        }
    }
}