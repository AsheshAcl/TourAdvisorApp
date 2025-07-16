package com.tour.advisor.data.screen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screen_config")
data class ScreenEntity(
    @PrimaryKey val screen_name: String,
    val jsonData: String // store raw json for each screen
)