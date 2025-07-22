package com.tour.advisor.data.screen

import com.tour.advisor.data.screen.mapper.ScreenMapper
import com.tour.advisor.domain.screen.ScreenRepository
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.data.screen.model.ScreenConfig

class ScreenRepositoryImpl(private val remoteSource: ScreenRemoteDataSource,
                           private val dao: ScreenDao,
                           private val logger: LoggerService
): ScreenRepository {
    override suspend fun getScreens(): List<ScreenConfig> {
        return try {
            val remoteScreens = remoteSource.getRemoteScreens()
            if (!remoteScreens.isNullOrEmpty()) {
                dao.insertScreens(remoteScreens.map { ScreenMapper.toEntity(it) })
                remoteScreens
            } else {
                logger.logError("ScreenRepository", "Remote screens were empty or null. Using fallback from DB.")
                dao.getScreens().map { ScreenMapper.fromEntity(it) }
            }
        } catch (e: Exception) {
            logger.logError("ScreenRepository", "Using fallback from DB: ${e.message}")
            dao.getScreens().map {
                ScreenMapper.fromEntity(it)
            }
        }
    }
}