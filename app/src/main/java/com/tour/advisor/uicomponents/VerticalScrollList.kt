package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.data.screen.model.ApiTextComponent
import com.tour.advisor.data.screen.model.SmallCardImageComponent
import com.tour.advisor.data.screen.model.VerticalListComponent
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun VerticalScrollList(component: ComponentStateModel.VerticalList, homeViewModel: HomeViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = component.sectionHeader,
            style = component.sectionHeaderStyle.getTypography(),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 18.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                Row(modifier = Modifier.fillMaxWidth().clickable {
                    //Todo: Fix the clickable animation
                    homeViewModel.navigateToRoute(component.onClickRoute)
                }) {
                    component.fields.forEach { field ->
                        when (field) {
                            is ComponentStateModel.SmallCard -> SmallCardImage()
                            else -> {
                            }
                        }
                    }

                    Column(Modifier.padding(top = 4.dp)) {
                        component.fields.forEach { field ->
                            when (field) {
                                is ComponentStateModel.ApiText -> ApiTextComponent(Modifier, field)
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
