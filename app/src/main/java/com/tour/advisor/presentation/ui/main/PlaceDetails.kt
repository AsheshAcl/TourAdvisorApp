package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tour.advisor.logger.LoggerService
import org.koin.compose.koinInject

@Composable
fun PlaceDetailsScreen(modifier: Modifier, homeViewModel: HomeViewModel, navController: NavController) {
    val appLogger: LoggerService = koinInject()

    Column(modifier = Modifier.padding(10.dp)) {
//        placeDetailsConfig?.ui_components?.let { uiComponents -> CommonScreenRender(components = uiComponents, homeViewModel = homeViewModel) }
    }
}