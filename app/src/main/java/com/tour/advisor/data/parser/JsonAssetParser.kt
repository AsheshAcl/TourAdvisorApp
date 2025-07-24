package com.tour.advisor.data.parser

import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.utility.assetloader.AssetLoader
import kotlinx.serialization.json.Json

class JsonAssetParser(
    val logger: LoggerService,
    val assetLoader: AssetLoader,
    val json: Json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "componentType"
    }
) {

    /**
     * Parses a JSON file from assets into the given type.
     */
    inline fun <reified T> parseFromAsset(fileName: String): T? {
        return try {
            logger.logInfo("JsonAssetParser", "Reading $fileName...")
            val jsonStr = assetLoader.loadJson(fileName)
            json.decodeFromString<T>(jsonStr)
        } catch (e: Exception) {
            logger.logError("JsonAssetParser", "Error parsing $fileName: ${e.message}")
            null
        }
    }
}
