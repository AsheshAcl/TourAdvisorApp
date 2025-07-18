package com.tour.advisor.uicomponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun TextComponent(modifier: Modifier, component: ComponentStateModel.Text, fontWeight: FontWeight = FontWeight.Medium) {
    Text(
        text = component.value,
        style = component.style?.getTypography()?.copy(fontWeight = fontWeight) ?: MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}