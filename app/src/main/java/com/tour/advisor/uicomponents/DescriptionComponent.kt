package com.tour.advisor.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.screens.HomeViewModel

@Composable
fun DescriptionComponent(component: ComponentStateModel.Description, homeViewModel: HomeViewModel? = null) {
    Column {
        TextComponent(
            modifier = Modifier.padding(start = 16.dp, top = 18.dp),
            ComponentStateModel.Text(
                value = component.sectionHeader,
                style = component.sectionHeaderStyle
            )
        )
        component.value?.let {
            DescriptionText(
                text = it,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun DescriptionText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int = 3,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.primary
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }

    val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Column(modifier = modifier) {
        Text(
            text = text,
            style = style,
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { layoutResult ->
                if (!isExpanded) {
                    isTextOverflowing = layoutResult.hasVisualOverflow
                }
                textLayoutResult.value = layoutResult
            }
        )

        if (isTextOverflowing || isExpanded) {
            Text(
                text = if (isExpanded) "Less" else "More",
                style = style.copy(color = color),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}

@Preview
@Composable
fun DescriptionPreview(modifier: Modifier = Modifier) {
    DescriptionComponent(
        component = ComponentStateModel.Description(sectionHeader = "Description", sectionHeaderStyle = "titleMedium", value = "Machu Picchu - Ancient Incan citadel in Peru. Machu Picchu - Ancient Incan citadel in Peru" +
                "Machu Picchu - Ancient Incan citadel in Peru, Machu Picchu - Ancient Incan citadel in Peru Machu Picchu - Ancient Incan citadel in Peru" +
                "Machu Picchu - Ancient Incan citadel in Peru")
    )
}