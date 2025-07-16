package com.tour.advisor.domain.usecases

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.places.PlaceRepository

class PlacesUseCase(
    private val repository: PlaceRepository
) {
    suspend operator fun invoke(): List<PlaceModel> = repository.getPlaces()
}