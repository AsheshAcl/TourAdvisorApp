package com.tour.advisor.domain.places

import com.tour.advisor.data.places.model.PlaceModel

interface PlaceRepository {
    suspend fun getPlaces(): List<PlaceModel>
}