package com.tour.advisor.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tour.advisor.domain.mapper.DataMapper.Companion.toUi
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import com.tour.advisor.logger.LoggerService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getScreenConfigUseCase: ScreenConfigUseCase, logger: LoggerService): ViewModel() {
    private val TAG: String = this::class.java.simpleName

    private val _screenStateModels = MutableStateFlow<List<ScreenModels>>(emptyList())
    val screenStateModels: StateFlow<List<ScreenModels>> = _screenStateModels

    private lateinit var navController: NavController

    fun setNavController(navigationController: NavController) {
        navController = navigationController
    }

    fun navigateToRoute(route: String, isPopBack: Boolean = false, popupBackRoute: String? = null) {
        navController.navigate(route) {
            if(isPopBack && popupBackRoute != null) {
                popUpTo(popupBackRoute) { inclusive = true }
            }
        }
    }

    init {
        viewModelScope.launch {
            val screens = getScreenConfigUseCase()
            val screenConfigs = screens.map { it.toUi() }
            _screenStateModels.emit(screenConfigs)
        }
    }
}