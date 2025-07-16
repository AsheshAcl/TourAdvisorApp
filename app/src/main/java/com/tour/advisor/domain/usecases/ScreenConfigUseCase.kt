package com.tour.advisor.domain.usecases

import com.tour.advisor.domain.screen.ScreenRepository
import com.tour.advisor.data.screen.parser.model.ScreenConfig

class ScreenConfigUseCase(
    private val repository: ScreenRepository
) {
    suspend operator fun invoke(): List<ScreenConfig> = repository.getScreens()
}