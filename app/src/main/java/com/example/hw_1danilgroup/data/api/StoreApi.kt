package com.example.hw_1danilgroup.data.api

import com.example.hw_1danilgroup.data.models.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

        @GET("products")
        suspend fun getAllProduct(): List<ProductDto>

        @GET("products/{id}")
        suspend fun getProductById(@Path("id") id: Int): ProductDto
    }