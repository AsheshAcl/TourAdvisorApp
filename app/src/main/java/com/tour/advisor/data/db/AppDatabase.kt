package com.tour.advisor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tour.advisor.data.screen.ScreenDao
import com.tour.advisor.data.screen.ScreenEntity

@Database(entities = [ScreenEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenDao(): ScreenDao
}