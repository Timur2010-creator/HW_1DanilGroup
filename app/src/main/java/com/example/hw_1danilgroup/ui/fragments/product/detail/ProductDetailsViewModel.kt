package com.example.hw_1danilgroup.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.repository.ProductRepository
import com.example.hw_1danilgroup.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsDetailsViewModel(id: Int) : ViewModel(){

    private val repository = ProductRepository()

    private val _state = MutableStateFlow< UiState<ProductDto>>(UiState.Loading)

    val state: StateFlow<UiState<ProductDto>> = _state.asStateFlow()

    init {
        loadProduct(id)
    }

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val products = repository.getProductsById(id)
                _state.value = UiState.Success(products)
            }catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}