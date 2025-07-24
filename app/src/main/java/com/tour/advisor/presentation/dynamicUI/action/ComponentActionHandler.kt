package com.tour.advisor.presentation.dynamicUI.action

import androidx.compose.runtime.Composable
import com.tour.advisor.domain.models.ComponentStateModel

fun interface ComponentActionHandler {
    fun onAction(componentAction: ComponentAction)
}

typealias ComponentComposable = @Composable (ComponentStateModel, ComponentActionHandler) -> Unit