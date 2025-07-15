package com.tour.advisor.dynamicUI

import android.content.Context
import com.tour.advisor.dynamicUI.components.ScreenConfig
import com.tour.advisor.dynamicUI.components.componentModule
import com.tour.advisor.logger.LoggerService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ComponentParser(private val applicationContext: Context): KoinComponent {
    private val TAG: String = this::class.java.simpleName
    private val logger: LoggerService by inject<LoggerService>()

    private val _screenList = MutableSharedFlow<List<ScreenConfig>>(replay = 1)
    val screenList: SharedFlow<List<ScreenConfig>> = _screenList

    private val json = Json {
        serializersModule = componentModule
        classDiscriminator = "componentType"
        ignoreUnknownKeys = true
    }
    init {
        logger.logInfo(TAG, "LoggerService initialized")
    }

    suspend fun startComponentParse() {
        logger.logInfo(TAG, "startComponentParse")
        val jsonString = loadJson()
        logger.logInfo(TAG, "Parsed json: $jsonString")
        val screenList = parseScreenConfigs(jsonString)

        logger.logInfo(TAG, "Screen config ${screenList.size}")

        _screenList.emit(screenList)
    }

    private fun parseScreenConfigs(jsonStr: String): List<ScreenConfig> {
        return json.decodeFromString(jsonStr)
    }

    private fun loadJson(): String {
        var jsonString = ""
        try {
            jsonString = applicationContext.assets.open ("Screen.json").bufferedReader().use { it.readText() }
            logger.logInfo(TAG, "parsed successfully")
        } catch (e: Exception) {
            logger.logError(TAG, "parsed error: ${e.message}")
        }
        return jsonString
    }
}