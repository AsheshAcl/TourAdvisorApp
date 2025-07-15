package com.tour.advisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tour.advisor.ui.main.HomeScreen
import com.tour.advisor.ui.main.HomeViewModel
import com.tour.advisor.ui.main.PlaceDetailsScreen
import com.tour.advisor.ui.main.SplashScreen
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = koinInject()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(homeViewModel = homeViewModel, modifier = Modifier,
                navController = navController)
        }

        composable("home") {
            HomeScreen(homeViewModel = homeViewModel, modifier = Modifier,
                navController = navController)
        }

        composable("placeDetails") {
            PlaceDetailsScreen(homeViewModel = homeViewModel, modifier = Modifier,
                navController = navController)
        }
    }
}