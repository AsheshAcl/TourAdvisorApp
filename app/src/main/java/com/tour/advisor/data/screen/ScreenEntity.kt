package com.tour.advisor.data.screen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screen_config")
data class ScreenEntity(
    @PrimaryKey(autoGenerate = false)
    val screen_name: String,
    val screenTitle: String?,
    val route: String,
    val nextScreenRoute: String,
    val uiComponentsJson: String,
    val arguments: List<String>? = null,
    val autoNavigateAfter: Long? = null
)

data class UiComponent(
    val componentType: String,
    val data_source: String,
    val fields: List<Field>,
    val id: String,
    val onClickRoute: String,
    val section_header: String,
    val section_header_style: String,
    val showBack: String,
    val style: String,
    val title: String,
    val type: String,
    val value: String
)

data class Field(
    val componentType: String,
    val data_source: String,
    val id: String,
    val onClickRoute: String,
    val resource: String,
    val resources: List<String>,
    val style: String,
    val type: String,
    val value: String
)