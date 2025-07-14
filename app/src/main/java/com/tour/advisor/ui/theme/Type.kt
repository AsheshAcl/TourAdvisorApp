package com.tour.advisor.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@Composable
fun getTextStyleFromJson(style: String?): TextStyle {
    return when (style) {
        "displayLarge" -> MaterialTheme.typography.displayLarge
        "displayMedium" -> MaterialTheme.typography.displayMedium
        "displaySmall" -> MaterialTheme.typography.displaySmall
        "headlineLarge" -> MaterialTheme.typography.headlineLarge
        "headlineMedium" -> MaterialTheme.typography.headlineMedium
        "headlineSmall" -> MaterialTheme.typography.headlineSmall
        "titleLarge" -> MaterialTheme.typography.titleLarge
        "titleMedium" -> MaterialTheme.typography.titleMedium
        "titleSmall" -> MaterialTheme.typography.titleSmall
        "bodyLarge" -> MaterialTheme.typography.bodyLarge
        "bodyMedium" -> MaterialTheme.typography.bodyMedium
        "bodySmall" -> MaterialTheme.typography.bodySmall
        "labelLarge" -> MaterialTheme.typography.labelLarge
        "labelMedium" -> MaterialTheme.typography.labelMedium
        "labelSmall" -> MaterialTheme.typography.labelSmall
        else -> MaterialTheme.typography.bodyMedium
    }
}