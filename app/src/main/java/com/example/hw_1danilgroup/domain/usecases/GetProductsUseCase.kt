package com.example.hw_1danilgroup.domain.usecases

import com.example.hw_1danilgroup.domain.models.Product
import com.example.hw_1danilgroup.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product>{
        return repository.getProducts()
    }
}