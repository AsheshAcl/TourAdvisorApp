package com.tour.advisor.data.screen.parser

import com.tour.advisor.data.screen.parser.model.ScreenConfig

interface ComponentRemoteDataSource {
    suspend fun getRemoteScreens(): List<ScreenConfig>
}