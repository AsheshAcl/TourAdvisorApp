package com.tour.advisor.data.places

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.screen.ScreenRepository
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.data.screen.model.ScreenConfig
import com.tour.advisor.domain.places.PlaceRepository
import kotlinx.serialization.json.Json

class PlacesRepositoryImpl(private val remoteSource: DataRemoteDataSource,
                           private val dao: PlaceDao,
                           private val logger: LoggerService
): PlaceRepository {
    override suspend fun getPlaces(): List<PlaceModel> {
        return try {
            val remoteScreens = remoteSource.getRemotePlacesData()

            dao.insertPlace(remoteScreens.map {
                PlaceEntity(
                    placeName = it.place_name,
                    placeImage = it.place_image,
                    placeDescription = it.place_description,
                    placeCost = it.place_cost,
                    placeRating = it.place_rating
                )
            })
            remoteScreens
        } catch (e: Exception) {
            logger.logError("Repo", "Using fallback from DB: ${e.message}")
            dao.getPlaces().map {
                //todo: Check for the exception
                Json.decodeFromString("")
            }
        }
    }
}