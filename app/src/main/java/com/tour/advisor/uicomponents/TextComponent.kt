package com.tour.advisor.uicomponents

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tour.advisor.data.screen.model.TextComponent
import com.tour.advisor.data.screen.model.UIComponent
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant.TEXT_COMPONENT_NAME
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography
import com.tour.annotations.Component


@Composable
@Component(TEXT_COMPONENT_NAME)
fun TextComponentRenderer(component: ComponentStateModel) {
    val textComponent = component as? ComponentStateModel.Text ?: return
    TextComponent(Modifier.padding(8.dp), textComponent)
}


@Composable
fun TextComponent(modifier: Modifier, component: ComponentStateModel.Text, fontWeight: FontWeight = FontWeight.Medium) {
    Text(
        text = component.value,
        style = component.style?.getTypography()?.copy(fontWeight = fontWeight) ?: MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun TextComponent(modifier: Modifier, component: TextComponent, fontWeight: FontWeight = FontWeight.Medium) {
    Text(
        text = component.value,
        style = component.style?.getTypography()?.copy(fontWeight = fontWeight) ?: MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}