package com.example.hw_1danilgroup.ui.fragments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.repository.ProductRepository
import com.example.hw_1danilgroup.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _state = MutableStateFlow< UiState<List<ProductDto>>>(UiState.Loading)

    val state: StateFlow<UiState<List<ProductDto>>> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val products = repository.getProducts()
                _state.value = UiState.Success(products)
            }catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}