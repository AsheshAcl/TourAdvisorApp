package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.annotations.Component

@Composable
@Component(ComponentConstant.TOP_BAR_COMPONENT_NAME)
fun TopAppBar(component: ComponentStateModel) {
    val topAppBarComponent = component as? ComponentStateModel.TopBar ?: return
    TopAppBar(topAppBarComponent, navigateBack = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(component: ComponentStateModel.TopBar, navigateBack: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(0), title = {
            TextComponent(
                Modifier,
                ComponentStateModel.Text(value = component.title, style = "headlineLarge"),
                fontWeight = FontWeight.ExtraBold
            )
        },
        navigationIcon = {
            if(component.showBack == true) {
                IconButton(onClick = {
                    navigateBack.invoke()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Settings")
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        actions = {
            /*IconButton(onClick = { *//* handle action *//* }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }*/
        }
    )
}