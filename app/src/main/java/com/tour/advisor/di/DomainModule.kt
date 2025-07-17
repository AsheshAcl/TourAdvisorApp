package com.tour.advisor.di

import com.tour.advisor.domain.usecases.PlacesUseCase
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ScreenConfigUseCase(get()) }
    factory { PlacesUseCase(get()) }
}