package com.example.aviatickets.model.entity

data class OfferResponse(
    val id: String,
    val price: Int,
    val flight: Flight,
    val airline: Airline,
    val duration: Int
)
