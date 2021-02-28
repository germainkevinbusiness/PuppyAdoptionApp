package com.kevincodes.puppyadoptionapp.data

data class Puppy(
    val id: Int,
    val image: Int,
    val name: String,
    val gender: String,
    val isAdult: Boolean,
    val qualities: String,
    val adoptionPrice: Float
)