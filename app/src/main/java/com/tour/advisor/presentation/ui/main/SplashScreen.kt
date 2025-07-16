package com.tour.advisor.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tour.advisor.presentation.ui.main.constants.Screen
import com.tour.advisor.uicomponents.CommonScreenRender
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier, homeViewModel: HomeViewModel, navController: NavController) {
    val screenConfig = homeViewModel.screenList.collectAsState()
    val splashScreenConfig by remember(screenConfig.value) {
        mutableStateOf(
            screenConfig.value.find { it.screen_name == Screen.SPLASH_SCREEN }
        )
    }

    LaunchedEffect(Unit) {
        splashScreenConfig?.auto_navigate_after?.let { delay(it) }
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    splashScreenConfig?.ui_components?.let { it1 -> CommonScreenRender(components = it1, homeViewModel = homeViewModel) }
}