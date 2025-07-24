package com.tour.advisor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.ui.common.CommonScreen
import org.koin.compose.koinInject

@Composable
fun OnBoardingScreen(modifier: Modifier, homeViewModel: HomeViewModel? = null) {
    val appLogger: LoggerService = koinInject()
    val onboardingScreen = homeViewModel?.onboardingScreenConfig?.collectAsState()

    Column {
        onboardingScreen?.value?.components?.let {
            CommonScreen(
                components = it, homeViewModel = homeViewModel
            )
        }
    }
}