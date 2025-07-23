package com.tour.advisor.di

import androidx.room.Room
import com.tour.advisor.data.db.AppDatabase
import com.tour.advisor.data.db.DBMigration
import com.tour.advisor.data.screen.ScreenRepositoryImpl
import com.tour.advisor.data.parser.ScreenParser
import com.tour.advisor.data.screen.ScreenRemoteDataSource
import com.tour.advisor.data.parser.DataParser
import com.tour.advisor.data.parser.JsonAssetParser
import com.tour.advisor.data.places.DataRemoteDataSource
import com.tour.advisor.data.places.PlaceDao
import com.tour.advisor.data.places.PlacesRepositoryImpl
import com.tour.advisor.domain.places.PlaceRepository
import com.tour.advisor.domain.screen.ScreenRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "screen-db").addMigrations(DBMigration.MIGRATION_1_2).build()
    }
    single { JsonAssetParser(get(), get()) }

    // Screens module
    single { get<AppDatabase>().screenDao() }
    single<ScreenRemoteDataSource> { ScreenParser(get(), get()) }
    single<ScreenRepository> { ScreenRepositoryImpl(get(), get(), get()) }

    // Places module
    single { get<AppDatabase>().placeDao() }
    single<DataRemoteDataSource> { DataParser(get(), get()) }
    single<PlaceRepository> { PlacesRepositoryImpl(get(), get(), get()) }
}