package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.ui.main.constants.Screen
import com.tour.advisor.uicomponents.CommonScreenRender
import com.tour.advisor.uicomponents.LoadingComponent
import org.koin.compose.koinInject

@Composable
fun PlaceDetailsScreen(modifier: Modifier, homeViewModel: HomeViewModel, navController: NavController,
                       placeName: String) {
    val appLogger: LoggerService = koinInject()
    val placeDetailsScreenModel = homeViewModel.detailsScreenConfig.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(placeName) {
        homeViewModel.callDetailsApi(Screen.PLACE_DETAIL_SCREEN, placeName)
    }

    placeDetailsScreenModel.value?.components?.let { components ->
        CommonScreenRender(
            components = components,
            homeViewModel = homeViewModel
        )
    }

    if (uiState.isLoading) {
        LoadingComponent(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)),
        )
    }
}