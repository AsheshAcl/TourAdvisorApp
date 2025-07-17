package com.tour.advisor.presentation.utility

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.tour.advisor.R
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.models.ComponentItemModel

class UIUtils {
    companion object {

        val drawableMap = mapOf(
            "ic_app_icon" to R.drawable.ic_app_icon,
        )

        fun getDrawableIdFromMap(name: String): Int? = drawableMap[name]

        @Composable
        fun String.getDrawableIdFromName(): Int? {
            return getDrawableIdFromMap(this)
        }

        @Composable
        fun String.getTypography(): TextStyle {
            return when (this) {
                "displayLarge" -> MaterialTheme.typography.displayLarge
                "displayMedium" -> MaterialTheme.typography.displayMedium
                "displaySmall" -> MaterialTheme.typography.displaySmall
                "headlineLarge" -> MaterialTheme.typography.headlineLarge
                "headlineMedium" -> MaterialTheme.typography.headlineMedium
                "headlineSmall" -> MaterialTheme.typography.headlineSmall
                "titleLarge" -> MaterialTheme.typography.titleLarge
                "titleMedium" -> MaterialTheme.typography.titleMedium
                "titleSmall" -> MaterialTheme.typography.titleSmall
                "bodyMedium" -> MaterialTheme.typography.bodyMedium
                "bodyLarge" -> MaterialTheme.typography.bodyLarge
                "bodySmall" -> MaterialTheme.typography.bodySmall
                "labelLarge" -> MaterialTheme.typography.labelLarge
                "labelMedium" -> MaterialTheme.typography.labelMedium
                "labelSmall" -> MaterialTheme.typography.labelSmall
                else -> MaterialTheme.typography.bodySmall
            }
        }
    }
}