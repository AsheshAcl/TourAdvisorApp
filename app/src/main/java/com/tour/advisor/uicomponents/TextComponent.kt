package com.tour.advisor.uicomponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tour.advisor.presentation.dynamicUI.components.TextComponent
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun TextComponent(modifier: Modifier, component: TextComponent) {
    Text(
        text = component.value.orEmpty(),
        style = component.style?.getTypography() ?: MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}