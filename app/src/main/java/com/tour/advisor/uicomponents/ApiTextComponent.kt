package com.tour.advisor.uicomponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography

@Composable
fun ApiTextComponent(modifier: Modifier, component: ComponentStateModel.ApiText) {
    Text(
        text = component.value,
        style = component.style?.getTypography() ?: MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}