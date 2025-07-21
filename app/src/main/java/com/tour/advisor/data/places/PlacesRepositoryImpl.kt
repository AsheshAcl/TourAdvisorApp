package com.tour.advisor.data.places

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.mapper.DataMapper.Companion.toDomain
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.domain.places.PlaceRepository
import com.tour.advisor.domain.result.Response

class PlacesRepositoryImpl(private val remoteSource: DataRemoteDataSource,
                           private val dao: PlaceDao,
                           private val logger: LoggerService
): PlaceRepository {
    override suspend fun getPlaces(): Response<List<PlaceModel>> {
        return try {
            val remoteScreens = remoteSource.getRemotePlacesData()

            dao.insertPlace(remoteScreens.map {
                PlaceEntity(
                    placeName = it.placeName,
                    placeImage = it.placeImage,
                    placeDescription = it.placeDescription,
                    placeCost = it.placeCost,
                    placeLocation = it.placeLocation,
                    placeRating = it.placeRating,
                    additionalImages = it.additionalImages
                )
            })
            Response.Success(remoteScreens)
        } catch (e: Exception) {
            logger.logError("PlacesRepositoryImpl", "Using fallback from DB: ${e.message}")
            return try {
                val localEntities = dao.getPlaces()
                val localModels = localEntities.toDomain()
                Response.Success(localModels)
            } catch (dbException: Exception) {
                logger.logError("PlacesRepositoryImpl", "DB fallback failed: ${dbException.message}")
                Response.Error("DB fallback failed:", dbException)
            }
        }
    }

    override suspend fun getPlaceDetails(name: String): Response<PlaceModel> {
        return try {
            val placeEntity = dao.getPlaceDetails(name)
            val localModel = placeEntity.toDomain()
            Response.Success(localModel)
        } catch (dbException: Exception) {
            logger.logError("PlacesRepositoryImpl", "DB fallback failed: ${dbException.message}")
            Response.Error("DB fallback failed:", dbException)
        }
    }
}