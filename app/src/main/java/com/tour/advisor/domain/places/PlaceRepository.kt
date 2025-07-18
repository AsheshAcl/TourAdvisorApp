package com.tour.advisor.domain.places

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.result.Response

interface PlaceRepository {
    suspend fun getPlaces(): Response<List<PlaceModel>>
    suspend fun getPlaceDetails(name: String): Response<PlaceModel>
}