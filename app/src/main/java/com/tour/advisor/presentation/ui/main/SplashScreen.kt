package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tour.advisor.navigation.Route
import com.tour.advisor.uicomponents.CommonScreenRender
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val splashScreenStateModel = homeViewModel.splashScreenStateModel.collectAsState()
    LaunchedEffect(Unit) {
//        println("splashScreenConfig?.autoNavigateAfter ${splashScreenStateModel.value?.autoNavigateAfter}")
        delay(6000)
        homeViewModel.navigateToRoute(Route.HOME_SCREEN.route, true, Route.SPLASH_SCREEN.route)
    }

    Column {
        splashScreenStateModel.value?.components?.let {
            CommonScreenRender(
                components = it, homeViewModel = homeViewModel
            )
        }
    }
}