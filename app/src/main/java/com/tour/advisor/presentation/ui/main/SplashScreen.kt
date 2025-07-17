package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.uicomponents.CommonScreenRender
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val splashScreenStateModel = homeViewModel.splashScreenStateModel.collectAsState()
    LaunchedEffect(Unit) {
        println("splashScreenConfig?.autoNavigateAfter ${splashScreenStateModel.value?.autoNavigateAfter}")
        delay(3000)
        homeViewModel.navigateToRoute("home", true, "splash")
    }

    Column(modifier = Modifier.padding(10.dp)) {
        splashScreenStateModel.value?.components?.let {
            CommonScreenRender(
                components = it, homeViewModel = homeViewModel
            )
        }
    }
}