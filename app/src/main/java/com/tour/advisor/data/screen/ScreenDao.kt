package com.tour.advisor.data.screen

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScreenDao {
    @Query("SELECT * FROM screen_config")
    suspend fun getScreens(): List<ScreenEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreens(screens: List<ScreenEntity>)
}