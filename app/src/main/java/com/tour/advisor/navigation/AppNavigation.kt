package com.tour.advisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.presentation.ui.main.HomeScreen
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.ui.main.PlaceDetailsScreen
import com.tour.advisor.presentation.ui.main.SplashScreen
import com.tour.advisor.presentation.ui.main.constants.Screen

@Composable
fun AppNavigation(
    screensStateModels: State<List<ScreenModels>>,
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            val splashScreenConfig by remember(screensStateModels.value) {
                mutableStateOf(screensStateModels.value.find { it.name == Screen.SPLASH_SCREEN })
            }
            splashScreenConfig?.let { it1 -> SplashScreen(modifier = Modifier, homeViewModel, it1) }
        }

        composable("home") {
            val homeScreenConfig by remember(screensStateModels.value) {
                mutableStateOf(screensStateModels.value.find { it.name == Screen.HOME_SCREEN })
            }
            homeScreenConfig?.let { it1 ->
                HomeScreen(
                    modifier = Modifier,
                    homeViewModel = homeViewModel,
                    it1
                )
            }
        }

        composable("placeDetails") {
            val detailsScreenConfig by remember(screensStateModels.value) {
                mutableStateOf(screensStateModels.value.find { it.name == Screen.HOME_SCREEN })
            }
            detailsScreenConfig?.let { it1 ->
                PlaceDetailsScreen(homeViewModel = homeViewModel, modifier = Modifier,
                    navController = navController)
            }
        }
    }
}