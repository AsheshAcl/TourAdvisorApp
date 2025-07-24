package com.tour.advisor.domain.usecases

import com.tour.advisor.domain.mapper.DataMapper.Companion.toComponentItem
import com.tour.advisor.domain.models.InfoItemModel
import com.tour.advisor.domain.places.PlaceRepository
import com.tour.advisor.domain.result.Response

class PlacesUseCase(
    private val repository: PlaceRepository
) {
    suspend fun getPlaces(): Response<List<InfoItemModel>> {
        return when (val response = repository.getPlaces()) {
            is Response.Success -> Response.Success(response.data.map { it.toComponentItem() })
            is Response.Error -> Response.Error(response.errorMessage)
        }
    }
    suspend fun getPlaceDetails(name: String): Response<InfoItemModel> {
        return when (val response = repository.getPlaceDetails(name)) {
            is Response.Success -> Response.Success(response.data.toComponentItem())
            is Response.Error -> Response.Error(response.errorMessage)
        }
    }
}