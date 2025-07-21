package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tour.advisor.domain.models.ComponentStateModel

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
        actions = {
            /*IconButton(onClick = { *//* handle action *//* }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }*/
        }
    )
}