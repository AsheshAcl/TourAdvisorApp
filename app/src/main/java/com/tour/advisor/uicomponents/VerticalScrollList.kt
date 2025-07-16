package com.tour.advisor.uicomponents

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
import com.tour.advisor.presentation.dynamicUI.components.ApiTextComponent
import com.tour.advisor.presentation.dynamicUI.components.SmallCardImageComponent
import com.tour.advisor.presentation.dynamicUI.components.VerticalListComponent
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun VerticalScrollList(component: VerticalListComponent) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = component.section_header,
            style = component.section_header_style.getTypography(),
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column {

                    }
                    component.fields.forEach { field ->
                        when (field) {
                            is SmallCardImageComponent -> SmallCardImage()
                            else -> {
                            }
                        }
                    }

                    Column(Modifier.padding(top = 4.dp)) {
                        component.fields.forEach { field ->
                            when (field) {
                                is ApiTextComponent -> ApiTextComponent(Modifier, field)
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
