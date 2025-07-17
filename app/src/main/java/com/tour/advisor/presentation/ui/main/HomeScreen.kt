package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.uicomponents.CommonScreenRender

@Composable
fun HomeScreen(modifier: Modifier, homeViewModel: HomeViewModel) {
    val homeScreenModel = homeViewModel.homeScreenConfig.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getPlaces()
    }

    LazyColumn(
        modifier = modifier
//            .fillMaxSize()
            .padding(10.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        homeScreenModel.value?.components?.let { components ->
            items(components) { component ->
                CommonScreenRender(
                    components = listOf(component),
                    homeViewModel = homeViewModel
                )
            }
        }
    }
    /*homeScreenModel.value?.components?.let { components ->
        CommonScreenRender(
            components = components,
            homeViewModel = homeViewModel
        )
    }*/
}