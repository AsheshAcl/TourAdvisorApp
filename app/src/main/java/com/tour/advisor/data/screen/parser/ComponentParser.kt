package com.tour.advisor.data.screen.parser

import android.content.Context
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.data.screen.parser.model.ScreenConfig
import com.tour.advisor.presentation.dynamicUI.components.componentModule
import kotlinx.serialization.json.Json

class ComponentParser(
    private val context: Context,
    private val logger: LoggerService
) : ComponentRemoteDataSource {

    private val json = Json {
        serializersModule = componentModule
        classDiscriminator = "componentType"
        ignoreUnknownKeys = true
    }

    override suspend fun getRemoteScreens(): List<ScreenConfig> {
        logger.logInfo("ComponentParser", "Reading JSON...")
        val jsonStr = loadJson()
        return parseScreenConfigs(jsonStr)
    }

    private fun parseScreenConfigs(jsonStr: String): List<ScreenConfig> {
        return json.decodeFromString(jsonStr)
    }

    private fun loadJson(): String {
        return try {
            context.assets.open("Screen.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            logger.logError("ComponentParser", "Error: ${e.message}")
            ""
        }
    }
}