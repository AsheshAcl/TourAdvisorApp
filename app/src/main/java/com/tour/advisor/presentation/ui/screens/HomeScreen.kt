package com.tour.advisor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tour.advisor.presentation.ui.constants.Screen

@Composable
fun HomeScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val homeScreenModel = homeViewModel.homeScreenConfig.collectAsState()

    LaunchedEffect(Unit) {
//        homeViewModel.getPlaces(screen = Screen.HOME_SCREEN)
    }

    Column {
        homeScreenModel.value?.components?.let {
            /*CommonScreen(
                components = it, homeViewModel = homeViewModel
            )*/
        }
    }
}