package com.tour.advisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.tour.advisor.navigation.AppNavigation
import com.tour.advisor.presentation.ui.main.HomeViewModel
import com.tour.advisor.presentation.ui.theme.TourAdvisorAppTheme
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TourAdvisorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val navController = rememberNavController()
                        val homeViewModel: HomeViewModel = koinInject()
                        LaunchedEffect(Unit) {
                            homeViewModel.setNavController(navController)
                        }

                        AppNavigation(navController, homeViewModel)
                    }
                }
            }
        }
    }
}