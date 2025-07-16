package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.presentation.dynamicUI.components.ApiTextComponent
import com.tour.advisor.presentation.dynamicUI.components.HorizontalListComponent
import com.tour.advisor.presentation.dynamicUI.components.LongCardImageComponent
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun HorizontalScrollList(
    component: HorizontalListComponent, homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = component.section_header,
            style = component.section_header_style.getTypography(),
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                Column {
                    Column {
                        component.fields.forEach { field ->
                            when (field) {
                                is LongCardImageComponent -> LongCardImage()
                                is ApiTextComponent -> ApiTextComponent(Modifier.padding(start = 10.dp),
                                    field)
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
