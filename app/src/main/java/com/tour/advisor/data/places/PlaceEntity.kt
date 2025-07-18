package com.tour.advisor.data.places

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val placeName: String,
    val placeImage: String? = null,
    val placeDescription: String? = null,
    val placeCost: String? = null,
    val placeRating: Double? = null,

)