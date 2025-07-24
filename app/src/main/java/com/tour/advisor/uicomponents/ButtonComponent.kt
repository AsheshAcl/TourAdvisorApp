package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.dynamicUI.action.ComponentAction
import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.utility.UIUtils.Companion.getTypography
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.BUTTON_COMPONENT_NAME)
fun ButtonComponentRenderer(component: ComponentStateModel, actionHandler: ComponentActionHandler) {
    val buttonComponent = component as? ComponentStateModel.Button ?: return
    ButtonComponent(Modifier, buttonComponent, actionHandler)
}

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    component: ComponentStateModel.Button,
    actionHandler: ComponentActionHandler
) {
    val backgroundColor = MaterialTheme.colorScheme.primary

    val contentColor = contentColorFor(backgroundColor)

    val shape = RoundedCornerShape(8)

    Button(
        onClick = {
            component.action?.let {
                actionHandler.onAction(ComponentAction.ButtonAction(it))
            }
        },
        modifier = modifier.padding(20.dp).fillMaxWidth(),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(text = component.value, style = component.style.getTypography())
    }
}