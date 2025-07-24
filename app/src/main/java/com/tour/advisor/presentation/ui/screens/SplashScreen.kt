package com.tour.advisor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tour.advisor.navigation.Route
import com.tour.advisor.presentation.ui.common.CommonScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val splashScreenStateModel = homeViewModel.splashScreenStateModel.collectAsState()
    LaunchedEffect(Unit) {
//        println("splashScreenConfig?.autoNavigateAfter ${splashScreenStateModel.value?.autoNavigateAfter}")
        homeViewModel.showHideLoading(isShow = true)
        delay(3000)
        homeViewModel.showHideLoading(isShow = false)
        homeViewModel.navigateToRoute(Route.ONBOARDING_SCREEN.route, true, Route.SPLASH_SCREEN.route)
    }

    Column {
        splashScreenStateModel.value?.components?.let {
            CommonScreen(
                components = it, homeViewModel = homeViewModel
            )
        }
    }
}