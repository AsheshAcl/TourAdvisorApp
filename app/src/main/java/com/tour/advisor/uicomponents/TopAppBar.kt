package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.tour.advisor.presentation.dynamicUI.components.TopAppBarComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(component: TopAppBarComponent) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        title = { Text(component.title) },
        navigationIcon = {
            IconButton(onClick = { /* handle back */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Back")
            }
        },
        actions = {
            /*IconButton(onClick = { *//* handle action *//* }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }*/
        }
    )
}