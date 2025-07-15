package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.dynamicUI.components.IconComponent
import com.tour.advisor.dynamicUI.components.SplashComponent
import com.tour.advisor.dynamicUI.components.TextComponent

@Composable
fun SplashComponent(component: SplashComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        component.fields.forEach { field ->
            when (field) {
                is IconComponent -> IconComponent(
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp),
                    field
                )
                is TextComponent -> TextComponent(field)
                else -> {
                }
//                is LoadingIndicatorComponent -> RenderLoadingIndicator(field)
            }
        }
    }
}