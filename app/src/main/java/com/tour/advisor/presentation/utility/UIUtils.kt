package com.tour.advisor.presentation.utility

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.tour.advisor.R
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.models.ComponentItemModel

class UIUtils {
    companion object {

        val drawableMap = mapOf(
            "ic_app_icon" to R.drawable.ic_app_icon,
            "ic_travel_splash" to R.drawable.ic_travel_splash,
            "ic_onboarding_1" to R.drawable.ic_onboarding_1,
            "ic_onboarding_2" to R.drawable.ic_onboarding_2
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

        private fun String.toFontWeight(): FontWeight = when (this.lowercase()) {
            "thin"        -> FontWeight.Thin
            "extralight"  -> FontWeight.ExtraLight
            "light"       -> FontWeight.Light
            "normal", "regular" -> FontWeight.Normal
            "medium"      -> FontWeight.Medium
            "semibold"    -> FontWeight.SemiBold
            "bold"        -> FontWeight.Bold
            "extrabold"   -> FontWeight.ExtraBold
            "black"       -> FontWeight.Black
            else          -> FontWeight.Normal // default
        }

        fun ComponentStyle.getTypography(): TextStyle {
            return TextStyle(
                fontSize = fontSize?.sp ?: TextUnit.Unspecified,
                fontWeight = fontWeight?.toFontWeight() ?: FontWeight.Normal
            )
        }
    }
}

enum class FontWeightType {
    Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black;

    fun toFontWeight(): FontWeight = when (this) {
        Thin -> FontWeight.Thin
        ExtraLight -> FontWeight.ExtraLight
        Light -> FontWeight.Light
        Normal -> FontWeight.Normal
        Medium -> FontWeight.Medium
        SemiBold -> FontWeight.SemiBold
        Bold -> FontWeight.Bold
        ExtraBold -> FontWeight.ExtraBold
        Black -> FontWeight.Black
    }
}

data class ComponentStyle(
    val fontSize: Int? = null,
    val fontWeight: FontWeightType? = null,
)