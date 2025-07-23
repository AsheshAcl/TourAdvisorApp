package com.tour.advisor.uicomponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.BUTTON_COMPONENT_NAME)
fun ButtonComponentRenderer(component: ComponentStateModel) {
    val buttonComponent = component as? ComponentStateModel.Text ?: return
    ButtonComponent(Modifier, buttonComponent)
}

@Composable
fun ButtonComponent(modifier: Modifier = Modifier, componentModel: ComponentStateModel) {
    val component = componentModel as? ComponentStateModel.Button ?: return
    val backgroundColor = MaterialTheme.colorScheme.primary

    val contentColor = contentColorFor(backgroundColor)

    val shape = RoundedCornerShape(8)

    Button(
        onClick = {
//            component.onClickAction?.let(onClick)
        },
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(text = component.value, style = component.style.getTypography())
    }
}