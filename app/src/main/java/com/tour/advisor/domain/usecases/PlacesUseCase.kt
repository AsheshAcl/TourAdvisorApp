package com.tour.advisor.domain.usecases

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.places.PlaceRepository
import com.tour.advisor.domain.result.Response

class PlacesUseCase(
    private val repository: PlaceRepository
) {
    suspend fun getPlaces(): Response<List<PlaceModel>> = repository.getPlaces()
    suspend fun getPlaceDetails(name: String): Response<PlaceModel> = repository.getPlaceDetails(name)
}