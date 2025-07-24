package com.tour.advisor.data.screen.mapper

import com.tour.advisor.data.screen.ScreenEntity
import com.tour.advisor.data.screen.model.ScreenConfig
import com.tour.advisor.data.screen.model.UIComponent
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object ScreenMapper {
    val json = Json {
        classDiscriminator = "componentType"
        ignoreUnknownKeys = true
    }
    fun fromEntity(entity: ScreenEntity): ScreenConfig {
        val decodedComponents = json.decodeFromString(
            ListSerializer(UIComponent.serializer()),
            entity.uiComponentsJson
        )

        return ScreenConfig(
            screen_name = entity.screen_name,
            screen_title = entity.screenTitle,
            route = entity.route,
            ui_components = decodedComponents,
            auto_navigate_after = entity.autoNavigateAfter
        )
    }

    fun toEntity(config: ScreenConfig): ScreenEntity {
        val encodedComponents = json.encodeToString(
            ListSerializer(UIComponent.serializer()),
            config.ui_components
        )

        return ScreenEntity(
            screen_name = config.screen_name,
            screenTitle = config.screen_title,
            route = config.route,
            uiComponentsJson = encodedComponents,
            autoNavigateAfter = config.auto_navigate_after
        )
    }
}