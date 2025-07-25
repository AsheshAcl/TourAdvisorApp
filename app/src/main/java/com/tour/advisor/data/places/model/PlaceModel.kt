package com.tour.advisor.data.places.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceModel(
    @SerialName("place_name")
    val placeName: String,
    @SerialName("place_image")
    val placeImage: String? = null,
    @SerialName("place_description")
    val placeDescription: String? = null,
    @SerialName("place_cost")
    val placeCost: String? = null,
    @SerialName("place_location")
    val placeLocation: String? = null,
    @SerialName("place_rating")
    val placeRating: Double? = null,
    @SerialName("additional_images")
    val additionalImages: List<String>? = null
)