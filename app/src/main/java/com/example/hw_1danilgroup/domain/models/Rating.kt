package com.example.hw_1danilgroup.domain.models

data class Rating(
    val rate: Double,
    val count: Int
)
{
    companion object{
        fun empty(): Rating{
            return Rating(
                rate = 0.0 ,
                count = 0
            )
        }
    }
}
