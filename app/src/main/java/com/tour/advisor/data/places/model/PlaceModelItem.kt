package com.tour.advisor.data.places.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceModel(
    @SerialName("place_name")
    val placeName: String,
    @SerialName("place_image")
    val placeImage: String?,
    @SerialName("place_description")
    val placeDescription: String?,
    @SerialName("place_cost")
    val placeCost: String?,
    @SerialName("place_rating")
    val placeRating: Double?
)