package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.dynamicUI.components.TextComponent
import com.tour.advisor.utility.UIUtils.Companion.getTypography

@Composable
fun TextComponent(component: TextComponent) {
    Text(
        text = component.value.orEmpty(),
        style = component.style?.getTypography() ?: MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(8.dp)
    )
}