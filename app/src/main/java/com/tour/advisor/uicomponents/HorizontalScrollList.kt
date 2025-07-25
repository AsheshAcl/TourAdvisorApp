package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.InfoItemModel
import com.tour.advisor.presentation.dynamicUI.action.ComponentAction
import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.ui.screens.HomeViewModel
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.HORIZONTAL_LIST_COMPONENT_NAME)
fun HorizontalScrollList(
    componentModel: ComponentStateModel, actionHandler: ComponentActionHandler? = null,
    homeViewModel: HomeViewModel? = null
) {
    LaunchedEffect(Unit) {
        componentModel.dataSource?.let {
            actionHandler?.onAction(ComponentAction.FetchData(dataSource = it))
        }
    }
    val component = componentModel as? ComponentStateModel.HorizontalList ?: return
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
                if (componentData is InfoItemModel) {
                    Column(Modifier.clickable {
                        //Todo: Fix the clickable animation
                        actionHandler?.onAction(
                            componentAction = ComponentAction.NavigateToRoute(
                                route = component.onClickRoute,
                                param = "/${componentData.title}"
                            )
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
                        componentData.rightTag?.let {
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
