package com.tour.advisor.data.places

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val placeName: String? = null,
    val placeImage: String? = null,
    val placeDescription: String? = null,
    val placeCost: String? = null,
    val placeRating: Double? = null,

)