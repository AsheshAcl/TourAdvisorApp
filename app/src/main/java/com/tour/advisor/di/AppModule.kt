package com.tour.advisor.di

import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.utility.assetloader.AndroidAssetLoader
import com.tour.advisor.presentation.utility.assetloader.AssetLoader
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
    single<AssetLoader> { AndroidAssetLoader(androidContext()) }
}