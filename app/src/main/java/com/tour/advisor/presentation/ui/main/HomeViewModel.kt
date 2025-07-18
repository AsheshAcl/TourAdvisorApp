package com.tour.advisor.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.mapper.DataMapper.Companion.toUi
import com.tour.advisor.domain.models.ComponentItemModel
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.domain.result.Response
import com.tour.advisor.domain.usecases.PlacesUseCase
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.dynamicUI.mapper.ComponentsMapper.Companion.toComponentItem
import com.tour.advisor.presentation.ui.main.constants.Screen
import com.tour.advisor.presentation.ui.main.state.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getScreenConfigUseCase: ScreenConfigUseCase,
                    private val placeUseCase: PlacesUseCase,
                    private val logger: LoggerService): ViewModel() {
    private val TAG: String = this::class.java.simpleName

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private val _screenStateModels = MutableStateFlow<List<ScreenModels>>(emptyList())
    val screenStateModels: StateFlow<List<ScreenModels>> = _screenStateModels

    private val _placesStateModels = MutableStateFlow<List<PlaceModel>>(emptyList())
    val placesStateModels: StateFlow<List<PlaceModel>> = _placesStateModels

    val splashScreenStateModel = MutableStateFlow<ScreenModels?>(null)
    val onboardingScreenConfig = MutableStateFlow<ScreenModels?>(null)
    val homeScreenConfig = MutableStateFlow<ScreenModels?>(null)
    val detailsScreenConfig = MutableStateFlow<ScreenModels?>(null)

    private lateinit var navController: NavController

    fun setNavController(navigationController: NavController) {
        navController = navigationController
    }

    fun navigateToRoute(route: String, isPopBack: Boolean = false, popupBackRoute: String? = null, argument: String? = "") {
        navController.navigate("$route$argument") {
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

            updateScreenConfigs(screenConfigs)
        }
    }

    private fun showHideLoading(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isShow)
    }

    private fun showErrorMessage(isShow: Boolean, errorMsg: String? = null) {
        if(isShow) {
            _uiState.value = _uiState.value.copy(errorMessage = errorMsg)
        } else
            _uiState.value = _uiState.value.copy(errorMessage = "")
    }

    private fun updateScreenConfigs(screenStateModels: List<ScreenModels>) {
        screenStateModels.forEach { screenModel ->
            when (screenModel.name) {
                Screen.SPLASH_SCREEN -> {
                    splashScreenStateModel.value = screenModel
                }

                Screen.ONBOARDING_SCREEN -> {
                    onboardingScreenConfig.value = screenModel
                }

                Screen.HOME_SCREEN -> {
                    homeScreenConfig.value = screenModel
                }

                Screen.PLACE_DETAIL_SCREEN -> {
                    detailsScreenConfig.value = screenModel
                }

                else -> {

                }
            }
        }
    }

    fun getPlaces(screen: Screen) {
        logger.logInfo(TAG, "Calling api - getPlaces")
        showHideLoading(isShow = true)
        viewModelScope.launch {
            when (val result = placeUseCase.getPlaces()) {
                is Response.Success -> {
                    updateComponentList(screen, "nearby_places", result.data)
                    delay(5000)
                    showHideLoading(isShow = false)
                    showErrorMessage(isShow = false)
                }
                is Response.Error -> {
                    showErrorMessage(isShow = true, errorMsg = result.errorMessage)
                    showHideLoading(isShow = false)
                }
            }
            logger.logInfo(TAG, "Finished api - getPlaces")
        }
    }

    fun callDetailsApi(screen: Screen, name: String) {
        getPlaceDetails(screen, name)
    }

    private fun getPlaceDetails(screen: Screen, name: String) {
        logger.logInfo(TAG, "Calling api - getPlaceDetails")
        showHideLoading(isShow = true)
        viewModelScope.launch {
            when (val result = placeUseCase.getPlaceDetails(name)) {
                is Response.Success -> {
                    logger.logInfo(TAG, "Api response - getPlaceDetails ${result.data}")
//                    updateComponentList(screen, "nearby_places", result.data)
                    delay(5000)
                    showHideLoading(isShow = false)
                    showErrorMessage(isShow = false)
                }
                is Response.Error -> {
                    showErrorMessage(isShow = true, errorMsg = result.errorMessage)
                    showHideLoading(isShow = false)
                }
            }
            logger.logInfo(TAG, "Finished api - getPlaceDetails")
        }
    }

    private suspend fun updateComponentList(
        screen: Screen,
        componentId: String,
        newItems: List<PlaceModel>
    ) {
        // Update this code to update component anywhere in the screen
        /*val updatedScreens = _screenStateModels.value.map { screenModel ->
            if (screenModel.name == screen) {
                val updatedComponents = updateNearByPlaceStateModels(componentId, screenModel.components, newItems)
                screenModel.copy(components = updatedComponents)
            } else {
                screenModel
            }
        }*/

        val components = homeScreenConfig.value?.components ?: return

        val updatedComponents = updatePlaceIntoStateModels(
            componentId = componentId,
            components = components,
            newItems = newItems
        )

        val updatedHomeScreen = homeScreenConfig.value?.copy(components = updatedComponents)

        updatedHomeScreen?.let { homeScreenConfig.emit(it) }
    }

    private fun updatePlaceIntoStateModels(
        componentId: String,
        components: List<ComponentStateModel>,
        newItems: List<PlaceModel>
    ): List<ComponentStateModel> {
        val updatedComponents = components.map { component ->
            var updatedComponent: ComponentStateModel = component
            if (component.dataSource == componentId) {
                when (component) {
                    is ComponentStateModel.HorizontalList -> {
                        val mappedItems: List<ComponentItemModel> = newItems.map { it.toComponentItem() }
                        updatedComponent = component
                        updatedComponent.items = mappedItems
                    }
                    is ComponentStateModel.VerticalList -> {
                        val mappedItems: List<ComponentItemModel> = newItems.map { it.toComponentItem() }
                        updatedComponent = component
                        updatedComponent.items = mappedItems
                    }
                    else -> {

                    }
                }
            }
            updatedComponent
        }
        return updatedComponents
    }
}