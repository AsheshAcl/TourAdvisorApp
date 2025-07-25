package com.tour.advisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tour.advisor.presentation.ui.common.CommonScreen
import com.tour.advisor.presentation.ui.screens.HomeViewModel

@Composable
fun AppNavigation(
    navController: NavHostController, homeViewModel: HomeViewModel
) {
    val screenConfig = homeViewModel.screenStateModels.collectAsState()
    val firstScreen = if (screenConfig.value.isNotEmpty()) screenConfig.value[0] else null
    firstScreen?.route?.let { route ->
        NavHost(navController = navController, startDestination = route) {
            screenConfig.value.forEach { config ->
                config.route?.let {
                    if (!config.arguments.isNullOrEmpty()) {
                        composable(config.route, arguments = config.arguments.map { argument ->
                            navArgument(argument) { type = NavType.StringType }
                        }) { _ ->
                            CommonScreen(
                                modifier = Modifier,
                                screenState = config,
                                homeViewModel = homeViewModel
                            )
                        }
                    } else {
                        composable(it) {
                            CommonScreen(
                                modifier = Modifier,
                                screenState = config,
                                homeViewModel = homeViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}