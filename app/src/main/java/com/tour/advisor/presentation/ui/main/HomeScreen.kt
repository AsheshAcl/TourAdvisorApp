package com.tour.advisor.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.uicomponents.CommonScreenRender

@Composable
fun HomeScreen(modifier: Modifier, homeViewModel: HomeViewModel, stateModel: ScreenModels) {

    Column(modifier = Modifier.padding(10.dp)) {
        CommonScreenRender(components = stateModel.components, homeViewModel = homeViewModel)
    }

}