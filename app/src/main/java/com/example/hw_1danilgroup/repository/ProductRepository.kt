package com.example.hw_1danilgroup.repository

import com.example.hw_1danilgroup.data.api.RetrofitService
import com.example.hw_1danilgroup.data.models.ProductDto

class ProductRepository {
    suspend fun getProducts(): List<ProductDto>{
        return RetrofitService.api.getAllProduct()
    }
}