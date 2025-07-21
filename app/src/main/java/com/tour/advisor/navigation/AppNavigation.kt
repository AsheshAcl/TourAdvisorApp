package com.tour.advisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tour.advisor.presentation.ui.screens.HomeScreen
import com.tour.advisor.presentation.ui.screens.HomeViewModel
import com.tour.advisor.presentation.ui.screens.PlaceDetailsScreen
import com.tour.advisor.presentation.ui.screens.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController, homeViewModel: HomeViewModel
) {
    NavHost(navController = navController, startDestination = Route.SPLASH_SCREEN.route) {
        composable(Route.SPLASH_SCREEN.route) {
            SplashScreen(modifier = Modifier, homeViewModel)
        }
        composable(Route.HOME_SCREEN.route) {
            HomeScreen(
                modifier = Modifier, homeViewModel = homeViewModel
            )
        }
        composable(Route.DETAILS_SCREEN.route,
            arguments = listOf(navArgument(NavArgument.PLACE_NAME) {
                type = NavType.StringType
            })) { backStackEntry ->
            val placeName = backStackEntry.arguments?.getString(NavArgument.PLACE_NAME) ?: ""
            PlaceDetailsScreen(
                homeViewModel = homeViewModel, modifier = Modifier, navController = navController,
                placeName = placeName
            )
        }
    }
}