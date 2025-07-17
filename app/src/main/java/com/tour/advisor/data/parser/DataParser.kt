package com.tour.advisor.data.parser

import com.tour.advisor.data.constants.Constants
import com.tour.advisor.data.places.DataRemoteDataSource
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.logger.LoggerService

class DataParser(
    private val jsonAssetParser: JsonAssetParser, private val logger: LoggerService
) : DataRemoteDataSource {
    override suspend fun getRemotePlacesData(): List<PlaceModel> {
        return jsonAssetParser.parseFromAsset<List<PlaceModel>>(Constants.PLACE_JSON_NAME) ?: emptyList()
    }
}