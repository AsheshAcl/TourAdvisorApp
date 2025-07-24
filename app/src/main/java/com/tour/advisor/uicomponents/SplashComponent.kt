package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.SPLASH_COMPONENT_NAME)
fun SplashComponent(componentModel: ComponentStateModel, actionHandler: ComponentActionHandler) {
    val component = componentModel as? ComponentStateModel.Splash ?: return
    Box(modifier = Modifier.fillMaxSize()) {
        ImageComponent(
            Modifier.fillMaxSize(),
            ComponentStateModel.ImageLocal(resource = "ic_travel_splash"),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            component.components.forEach { field ->
                when (field) {
                    is ComponentStateModel.Text -> TextComponent(
                        Modifier, field.copy(style = field.style),
                        fontWeight = FontWeight.ExtraBold
                    )
                    else -> {
                    }
//                is LoadingIndicatorComponent -> RenderLoadingIndicator(field)
                }
            }
        }
    }
}