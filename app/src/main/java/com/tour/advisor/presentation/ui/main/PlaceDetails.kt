package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.ui.main.constants.Screen
import com.tour.advisor.uicomponents.CommonScreenRender
import org.koin.compose.koinInject

@Composable
fun PlaceDetailsScreen(modifier: Modifier, homeViewModel: HomeViewModel, navController: NavController) {
    val appLogger: LoggerService = koinInject()
    val screenConfig = homeViewModel.screenList.collectAsState()
    val placeDetailsConfig by remember(screenConfig.value) {
        mutableStateOf(screenConfig.value.find { it.screen_name == Screen.PLACE_DETAILS_SCREEN })
    }

    LaunchedEffect(Unit) {
        homeViewModel.setNavController(navController)
    }

    Column(modifier = Modifier.padding(10.dp)) {
        placeDetailsConfig?.ui_components?.let { uiComponents -> CommonScreenRender(components = uiComponents, homeViewModel = homeViewModel) }
    }
}