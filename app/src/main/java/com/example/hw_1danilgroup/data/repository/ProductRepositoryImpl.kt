package com.example.hw_1danilgroup.data.repository

import com.example.hw_1danilgroup.data.datasource.RetrofitService
import com.example.hw_1danilgroup.data.datasource.StoreApi
import com.example.hw_1danilgroup.data.mappers.toDomain
import com.example.hw_1danilgroup.domain.models.Product
import com.example.hw_1danilgroup.domain.repository.ProductRepository

class ProductRepositoryImpl: ProductRepository {
    private val api: StoreApi = RetrofitService.api

    override suspend fun getProducts(): List<Product> {
        return api.getAllProduct().toDomain()
    }

    override suspend fun getProductById(productId: Int): Product {
        return api.getProductById(productId).toDomain()
    }
}
