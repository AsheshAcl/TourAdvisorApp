package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentItemModel
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.PlaceItemModel
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun HorizontalScrollList(
    component: ComponentStateModel.HorizontalList, homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextComponent(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 18.dp),
            ComponentStateModel.Text(
                value = component.sectionHeader,
                style = component.sectionHeaderStyle
            )
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(component.items) { componentData ->
                if (componentData is PlaceItemModel) {
                    Column(Modifier.clickable {
                        //Todo: Fix the clickable animation
                        homeViewModel.navigateToRoute(
                            route = component.onClickRoute, argument = "/${componentData.title}"
                        )
                    }) {
                        LongCardImage(ComponentStateModel.Image(url = componentData.imageUrl))
                        componentData.title?.let {
                            TextComponent(
                                Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                                    value = it, style = "labelSmall"
                                )
                            )
                        }
                        componentData.cost?.let {
                            TextComponent(
                                Modifier.padding(start = 10.dp), ComponentStateModel.Text(
                                    value = it, style = "labelMedium"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getValueForDataSource(dataSource: String?, componentData: ComponentItemModel) {
    when(componentData) {
        is PlaceItemModel -> {
//            if(dataSource == componentData)
        }
    }
}
