package com.tour.advisor.di

import com.tour.advisor.logger.AppLogger
import com.tour.advisor.logger.LoggerService
import org.koin.dsl.module

val loggerModule = module {
    single<LoggerService> { AppLogger }
}