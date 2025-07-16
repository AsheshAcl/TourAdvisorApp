package com.tour.advisor.di

import androidx.room.Room
import com.tour.advisor.data.db.AppDatabase
import com.tour.advisor.data.screen.ScreenRepositoryImpl
import com.tour.advisor.data.parser.ScreenParser
import com.tour.advisor.data.screen.ScreenRemoteDataSource
import com.tour.advisor.data.parser.DataParser
import com.tour.advisor.data.parser.JsonAssetParser
import com.tour.advisor.data.places.DataRemoteDataSource
import com.tour.advisor.domain.screen.ScreenRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "screen-db").build()
    }
    single { get<AppDatabase>().screenDao() }
    single { JsonAssetParser(get(), get(), get()) }
    single<ScreenRemoteDataSource> { ScreenParser(get(), get()) }
    single<DataRemoteDataSource> { DataParser(get(), get()) }
    single<ScreenRepository> { ScreenRepositoryImpl(get(), get(), get()) }
}