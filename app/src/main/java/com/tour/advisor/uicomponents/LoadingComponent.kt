package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
) {
    val size = 40.dp
    val color = MaterialTheme.colorScheme.primary

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = 4.dp
        )
    }
}