package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.InfoItemModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.ui.screens.HomeViewModel
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.VERTICAL_LIST_COMPONENT_NAME)
fun VerticalScrollList(componentModel: ComponentStateModel, homeViewModel: HomeViewModel? = null) {
    val component = componentModel as? ComponentStateModel.VerticalList ?: return
    Column(modifier = Modifier.fillMaxWidth()) {
        TextComponent(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 18.dp),
            ComponentStateModel.Text(
                value = component.sectionHeader, style = component.sectionHeaderStyle
            )
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            items(component.items) { componentData ->
                if (componentData is InfoItemModel) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //Todo: Fix the clickable animation
                            homeViewModel?.navigateToRoute(
                                route = component.onClickRoute,
                                argument = "/${componentData.title}"
                            )
                        }) {
                        SmallCardImage(ComponentStateModel.Image(url = componentData.imageUrl))

                        Column(Modifier.padding(top = 4.dp)) {
                            componentData.title?.let {
                                TextComponent(
                                    Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                                        value = it, style = "titleLarge"
                                    )
                                )
                            }
                            componentData.rightTag?.let {
                                TextComponent(
                                    Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                                        value = it, style = "titleMedium"
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
