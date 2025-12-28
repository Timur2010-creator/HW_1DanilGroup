package com.example.hw_1danilgroup.domain.repository

import com.example.hw_1danilgroup.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(productId: Int): Product
}