package com.tour.advisor.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tour.advisor.presentation.dynamicUI.ComponentParser
import com.tour.advisor.presentation.dynamicUI.components.ScreenConfig
import com.tour.advisor.logger.LoggerService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(componentParser: ComponentParser, logger: LoggerService): ViewModel() {
    private val TAG: String = this::class.java.simpleName

    private val _screenList = MutableStateFlow<List<ScreenConfig>>(emptyList())
    val screenList: StateFlow<List<ScreenConfig>> = _screenList

    private lateinit var navController: NavController

    fun setNavController(navigationController: NavController) {
        navController = navigationController
    }

    fun navigateToRoute(route: String) {
        navController.navigate(route)
    }

    init {
        viewModelScope.launch {
            componentParser.startComponentParse()

            componentParser.screenList.collect { screenList ->
                _screenList.emit(screenList)
                screenList.forEachIndexed { index, item ->
                    logger.logInfo(TAG, "Screen config Index: $index Item: ${item.screen_name}")
                }
            }
        }
    }
}