package com.tour.advisor.data.places

import com.tour.advisor.data.places.model.PlaceModel

interface DataRemoteDataSource {
    suspend fun getRemotePlacesData(): List<PlaceModel>
}