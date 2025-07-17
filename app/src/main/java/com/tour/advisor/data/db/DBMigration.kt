package com.tour.advisor.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DBMigration {
    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS places (
                id BLOB NOT NULL PRIMARY KEY,
                placeName TEXT,
                placeImage TEXT,
                placeDescription TEXT,
                placeCost TEXT,
                placeRating REAL           
            )
            """.trimIndent()
                )
            }
        }
    }
}