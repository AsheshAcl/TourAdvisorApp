package com.tour.advisor.data.parser

import android.content.Context
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.dynamicUI.components.componentModule
import com.tour.advisor.presentation.utility.assetloader.AssetLoader
import kotlinx.serialization.json.Json

class JsonAssetParser(
    val context: Context,
    val logger: LoggerService,
    val assetLoader: AssetLoader,
    val json: Json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "componentType"
        serializersModule = componentModule
    }
) {

    /**
     * Parses a JSON file from assets into the given type.
     */
    inline fun <reified T> parseFromAsset(fileName: String): T? {
        return try {
            logger.logInfo("JsonAssetParser", "Reading $fileName...")
            val jsonStr = assetLoader.loadJson("Screen.json")
            json.decodeFromString<T>(jsonStr)
        } catch (e: Exception) {
            logger.logError("JsonAssetParser", "Error parsing $fileName: ${e.message}")
            null
        }
    }
}
