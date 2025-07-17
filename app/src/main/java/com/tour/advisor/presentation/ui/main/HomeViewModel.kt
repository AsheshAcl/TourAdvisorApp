package com.tour.advisor.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.mapper.DataMapper.Companion.toUi
import com.tour.advisor.domain.models.ComponentItemModel
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.domain.usecases.PlacesUseCase
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.dynamicUI.mapper.ComponentsMapper.Companion.toComponentItem
import com.tour.advisor.presentation.ui.main.constants.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getScreenConfigUseCase: ScreenConfigUseCase,
                    private val placeUseCase: PlacesUseCase,
                    private val logger: LoggerService): ViewModel() {
    private val TAG: String = this::class.java.simpleName

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

            updateScreenConfigs(screenConfigs)
        }
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

                Screen.PLACE_DETAILS_SCREEN -> {
                    detailsScreenConfig.value = screenModel
                }

                // Optionally handle unknown screen
                else -> {
                    // Log or ignore
                }
            }
        }
    }

    fun getPlaces() {
        //Todo: Show loading for api call here
        viewModelScope.launch {
            val placeList = placeUseCase.invoke()
            placeList.forEach {
                logger.logInfo(TAG, "Place name: ${it.placeName} Place cost: ${it.placeCost}")
            }
//            _placesStateModels.emit(placeList)

            updateComponentList(Screen.HOME_SCREEN, "nearby_places", placeList)
//            updateComponentList(Screen.HOME_SCREEN, "recommended_places", placeList)
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