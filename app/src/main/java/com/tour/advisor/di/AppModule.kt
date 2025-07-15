package com.tour.advisor.di

import com.tour.advisor.dynamicUI.ComponentParser
import com.tour.advisor.ui.main.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
    single { ComponentParser(androidContext()) }
}