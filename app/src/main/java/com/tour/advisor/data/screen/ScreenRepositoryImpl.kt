package com.tour.advisor.data.screen

import com.tour.advisor.domain.screen.ScreenRepository
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.data.screen.model.ScreenConfig
import kotlinx.serialization.json.Json

class ScreenRepositoryImpl(private val remoteSource: ScreenRemoteDataSource,
                           private val dao: ScreenDao,
                           private val logger: LoggerService
): ScreenRepository {
    override suspend fun getScreens(): List<ScreenConfig> {
        return try {
            val remoteScreens = remoteSource.getRemoteScreens()

            dao.insertScreens(remoteScreens.map {
                ScreenEntity(
                    screen_name = it.screen_name.toString(),
//                    jsonData = Json.encodeToString(ScreenConfig.serializer(), it)
                    jsonData = ""
                )
            })
            remoteScreens
        } catch (e: Exception) {
            logger.logError("Repo", "Using fallback from DB: ${e.message}")
            dao.getScreens().map {
                Json.decodeFromString(it.jsonData)
            }
        }
    }
}