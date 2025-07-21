package com.tour.advisor.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.screens.HomeViewModel

@Composable
fun InfoComponent(component: ComponentStateModel.Info, homeViewModel: HomeViewModel? = null) {
    Column(Modifier.clickable {
        //Todo: Fix the clickable animation
        /*homeViewModel?.navigateToRoute(
            route = component.onClickRoute, argument = "/${componentData.title}"
        )*/
    }.background(color = Color.White)) {
        component.infoTitle?.let {
            TextComponent(
                Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                    value = it, style = "headlineLarge"
                )
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            component.leftTag?.let {
                TextComponent(
                    Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                        value = it, style = "bodyLarge"
                    )
                )
            }
            component.rightTag?.let {
                TextComponent(
                    Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                        value = it, style = "bodyLarge"
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoComponentPreview() {
    InfoComponent(ComponentStateModel.Info(
        infoTitle = "Machu Picchu",
        leftTag = "Beijing, China",
        rightTag = "$30/Per person"
    ))
}