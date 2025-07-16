package com.tour.advisor.data.places

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val placeName: String,
    val placeImage: String,
    val placeDescription: String,
    val placeCost: String,
    val placeRating: Double,

)