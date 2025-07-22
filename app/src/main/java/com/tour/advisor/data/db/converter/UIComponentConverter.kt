package com.tour.advisor.data.db.converter

import androidx.room.TypeConverter
import com.tour.advisor.data.screen.UiComponent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UIComponentConverter {
    @TypeConverter
    fun fromUIComponentList(value: List<UiComponent>?): String {
        return Json.encodeToString(value = value ?: emptyList())
    }

    @TypeConverter
    fun toUIComponentList(value: String): List<UiComponent> {
        return Json.decodeFromString(value)
    }
}

