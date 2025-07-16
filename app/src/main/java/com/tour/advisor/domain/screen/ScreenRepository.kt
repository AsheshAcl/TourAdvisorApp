package com.tour.advisor.domain.screen

import com.tour.advisor.data.screen.parser.model.ScreenConfig

interface ScreenRepository {
    suspend fun getScreens(): List<ScreenConfig>
}