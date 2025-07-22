package com.tour.advisor.data.screen

import com.tour.advisor.data.screen.model.ScreenConfig

interface ScreenRemoteDataSource {
    suspend fun getRemoteScreens(): List<ScreenConfig>?
}