package com.tour.advisor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.ui.constants.Screen
import org.koin.compose.koinInject

@Composable
fun PlaceDetailsScreen(modifier: Modifier, homeViewModel: HomeViewModel, navController: NavController,
                       placeName: String) {
    val appLogger: LoggerService = koinInject()
    val placeDetailsScreenModel = homeViewModel.detailsScreenConfig.collectAsState()

    LaunchedEffect(placeName) {
        homeViewModel.callDetailsApi(Screen.PLACE_DETAIL_SCREEN, placeName)
    }

    Column {
        placeDetailsScreenModel.value?.components?.let {
            /*CommonScreen(
                components = it, homeViewModel = homeViewModel
            )*/
        }
    }
}