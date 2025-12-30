package com.example.hw_1danilgroup.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.data.repository.ProductRepositoryImpl
import com.example.hw_1danilgroup.domain.models.Product
import com.example.hw_1danilgroup.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsDetailsViewModel(id: Int) : ViewModel(){

    private val repository = ProductRepositoryImpl()

    private val _state = MutableStateFlow< UiState<Product>>(UiState.Loading)

    val state: StateFlow<UiState<Product>> = _state.asStateFlow()

    init {
        loadProduct(id)
    }

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val products = repository.getProductById(id)
                _state.value = UiState.Success(products)
            }catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}