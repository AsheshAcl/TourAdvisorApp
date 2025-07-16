package com.tour.advisor.di

import androidx.room.Room
import com.tour.advisor.data.db.AppDatabase
import com.tour.advisor.data.screen.ScreenRepositoryImpl
import com.tour.advisor.data.screen.parser.ComponentParser
import com.tour.advisor.data.screen.parser.ComponentRemoteDataSource
import com.tour.advisor.domain.screen.ScreenRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "screen-db").build()
    }
    single { get<AppDatabase>().screenDao() }
    single<ComponentRemoteDataSource> { ComponentParser(get(), get()) }
    single<ScreenRepository> { ScreenRepositoryImpl(get(), get(), get()) }
}