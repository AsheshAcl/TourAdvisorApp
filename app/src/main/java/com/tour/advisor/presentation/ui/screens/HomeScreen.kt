package com.tour.advisor.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tour.advisor.presentation.ui.constants.Screen
import com.tour.advisor.presentation.ui.common.DynamicScreen
import com.tour.advisor.uicomponents.LoadingComponent

@Composable
fun HomeScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val homeScreenModel = homeViewModel.homeScreenConfig.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getPlaces(screen = Screen.HOME_SCREEN)
    }

    Column {
        homeScreenModel.value?.components?.let {
            DynamicScreen(
                components = it, homeViewModel = homeViewModel
            )
        }
    }

    if (uiState.isLoading) {
        LoadingComponent(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)),
        )
    }
}