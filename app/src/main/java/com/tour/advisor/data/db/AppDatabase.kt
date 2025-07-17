package com.tour.advisor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tour.advisor.data.places.PlaceDao
import com.tour.advisor.data.places.PlaceEntity
import com.tour.advisor.data.screen.ScreenDao
import com.tour.advisor.data.screen.ScreenEntity

@Database(entities = [ScreenEntity::class, PlaceEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenDao(): ScreenDao
    abstract fun placeDao(): PlaceDao
}