package com.tour.advisor.data.parser

import com.tour.advisor.data.screen.ScreenRemoteDataSource
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.data.screen.model.ScreenConfig

class ScreenParser(
    private val jsonAssetParser: JsonAssetParser, private val logger: LoggerService
) : ScreenRemoteDataSource {
    override suspend fun getRemoteScreens(): List<ScreenConfig> {
        return jsonAssetParser.parseFromAsset<List<ScreenConfig>>("Screen.json") ?: emptyList()
    }
}