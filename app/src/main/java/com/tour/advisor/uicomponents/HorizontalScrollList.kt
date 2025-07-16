package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun HorizontalScrollList(
    component: ComponentStateModel.HorizontalList, homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = component.sectionHeader,
            style = component.sectionHeaderStyle.getTypography(),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 18.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                Column {
                    Column(Modifier.clickable {
                        //Todo: Fix the clickable animation
                        homeViewModel.navigateToRoute(component.onClickRoute)
                    }) {
                        component.fields.forEach { field ->
                            when (field) {
                                is ComponentStateModel.LongCard -> LongCardImage()
                                is ComponentStateModel.ApiText -> ApiTextComponent(Modifier.padding(start = 10.dp), field)
//                                is SingleRatingComponent -> RenderRatingComponent(field)
                                else -> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
