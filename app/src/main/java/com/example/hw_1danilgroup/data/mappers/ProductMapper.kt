package com.example.hw_1danilgroup.data.mappers


import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.data.models.RatingDto
import com.example.hw_1danilgroup.domain.models.Product
import com.example.hw_1danilgroup.domain.models.Rating

fun ProductDto.toDomain(): Product{
    return Product(
        id = this.id ?: -1,
        title = this.title ?: "" ,
        price = this.price ?: 0.0,
        description = this.description ?: "" ,
        category = this.description ?: "",
        image = this.image ?: "",
        rating = this.rating?.toDomain() ?: Rating.empty()
    )
}
fun RatingDto.toDomain(): Rating {
    return Rating(
        rate = this.rate ?: 0.0,
        count = this.count ?: 0
    )
}