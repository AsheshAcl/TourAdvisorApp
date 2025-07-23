package com.tour.advisor.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.ui.screens.HomeViewModel
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.INFO_ROW_COMPONENT_NAME)
fun InfoComponent(componentModel: ComponentStateModel, homeViewModel: HomeViewModel? = null) {
    val component = componentModel as? ComponentStateModel.Info ?: return
    Column(Modifier.clickable {
        //Todo: Fix the clickable animation
        /*homeViewModel?.navigateToRoute(
            route = component.onClickRoute, argument = "/${componentData.title}"
        )*/
    }
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
            clip = false // shadow will be outside the shape
        )
        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        .background(Color.White)
        .padding(16.dp)
    ) {
        component.infoTitle?.let {
            TextComponent(
                Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                    value = it, style = "headlineLarge"
                )
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
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