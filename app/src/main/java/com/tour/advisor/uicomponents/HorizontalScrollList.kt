package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.dynamicUI.components.HorizontalListComponent
import com.tour.advisor.dynamicUI.components.SingleRatingComponent
import com.tour.advisor.dynamicUI.components.TextComponent
import com.tour.advisor.ui.main.HomeViewModel

@Composable
fun HorizontalScrollList(component: HorizontalListComponent,
                         homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = component.section_header,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) { // Replace with real data later from `data_source`
                Card(
                    onClick = {
                        homeViewModel.navigateToRoute(component.onClickRoute)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(180.dp)
                        .height(120.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        component.fields.forEach { field ->
                            when (field) {
                                is TextComponent -> TextComponent(field)
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
