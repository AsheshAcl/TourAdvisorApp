package com.tour.advisor.data.places

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places")
    suspend fun getPlaces(): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE placeName = :name")
    suspend fun getPlaceDetails(name: String): PlaceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(screens: List<PlaceEntity>)
}