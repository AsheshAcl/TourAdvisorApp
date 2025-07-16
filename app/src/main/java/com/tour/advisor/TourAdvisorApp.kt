package com.tour.advisor

import android.app.Application
import com.tour.advisor.di.appModule
import com.tour.advisor.di.dataModule
import com.tour.advisor.di.domainModule
import com.tour.advisor.di.loggerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class TourAdvisorApp: KoinComponent, Application() {
    override fun onCreate() {
        super.onCreate()
        koinApplication {
            startKoin {
                androidContext(this@TourAdvisorApp)
                modules(appModule, loggerModule, dataModule, domainModule)
            }
        }
    }
}