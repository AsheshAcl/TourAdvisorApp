package com.tour.advisor.presentation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tour.advisor.domain.mapper.DataMapper.Companion.toUi
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.InfoItemModel
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.domain.result.Response
import com.tour.advisor.domain.usecases.PlacesUseCase
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.dynamicUI.action.ComponentAction
import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler
import com.tour.advisor.presentation.ui.constants.Screen
import com.tour.advisor.presentation.ui.state.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val getScreenConfigUseCase: ScreenConfigUseCase,
                    private val placeUseCase: PlacesUseCase,
                    private val logger: LoggerService): ViewModel(), ComponentActionHandler {

    init {
        viewModelScope.launch {
            val screens = getScreenConfigUseCase()
            val screenConfigs = screens.map { it.toUi() }
            _screenStateModels.emit(screenConfigs)
//            _screenConfigStateModels.emit(screens)
        }
    }

    private val TAG: String = this::class.java.simpleName

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private val _screenStateModels = MutableStateFlow<List<ScreenModels>>(emptyList())
    private val screenStateModels: StateFlow<List<ScreenModels>> = _screenStateModels

    /*private val _placesStateModels = MutableStateFlow<List<InfoItemModel>>(emptyList())
    val placesStateModels: StateFlow<List<InfoItemModel>> = _placesStateModels*/

/*    private val _placeDetailsStateModels: MutableStateFlow<InfoItemModel?> = MutableStateFlow<InfoItemModel?>(null)
    val placeDetailsStateModels: StateFlow<InfoItemModel?> = _placeDetailsStateModels*/

    val splashScreenStateModel: StateFlow<ScreenModels?> = screenStateModels
        .map { it.find { model -> model.name == Screen.SPLASH_SCREEN } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val onboardingScreenConfig: StateFlow<ScreenModels?> = screenStateModels
        .map { it.find { model -> model.name == Screen.ONBOARDING_SCREEN } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val homeScreenConfig: StateFlow<ScreenModels?> = screenStateModels
        .map { it.find { model -> model.name == Screen.HOME_SCREEN } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val detailsScreenConfig: StateFlow<ScreenModels?> = screenStateModels
        .map { it.find { model -> model.name == Screen.PLACE_DETAIL_SCREEN } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

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

    fun navigateBack() {
        navController.popBackStack()
    }

    fun showHideLoading(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isShow)
    }

    private fun showErrorMessage(isShow: Boolean, errorMsg: String? = null) {
        if(isShow) {
            _uiState.value = _uiState.value.copy(errorMessage = errorMsg)
        } else
            _uiState.value = _uiState.value.copy(errorMessage = "")
    }

    private fun updateScreenModelByScreenName(name: Screen?, screenModel: ScreenModels) {
        _screenStateModels.value = _screenStateModels.value
            .filterNot { it.name == name } + screenModel
    }

    fun getPlaces(screen: Screen) {
        logger.logInfo(TAG, "Calling api - getPlaces")
        showHideLoading(isShow = true)
        viewModelScope.launch {
            when (val result = placeUseCase.getPlaces()) {
                is Response.Success -> {
//                    _placesStateModels.emit(result.data)
                    updateComponentList(screen, "nearby_places", result.data)
                    delay(2000)
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

    private fun updateComponentList(
        screen: Screen,
        componentId: String,
        newItems: List<InfoItemModel>
    ) {
        _screenStateModels.value.map { screenModel ->
            if (screenModel.name == screen) {
                val updatedComponents = updatePlaceListIntoStateModels(componentId, screenModel.components, newItems)
                updateScreenModelByScreenName(screenModel.name, screenModel.copy(components = updatedComponents))
            } else {
                screenModel
            }
        }
    }

    private fun updatePlaceListIntoStateModels(
        componentId: String,
        components: List<ComponentStateModel>,
        newItems: List<InfoItemModel>
    ): List<ComponentStateModel> {
        return components.map { component ->
            if (component.dataSource == componentId) {
                when (component) {
                    is ComponentStateModel.HorizontalList -> {
                        component.copy(items = newItems)
                    }
                    is ComponentStateModel.VerticalList -> {
                        component.copy(items = newItems)
                    }
                    else -> component
                }
            } else {
                component
            }
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
//                    _placeDetailsStateModels.emit(result.data)
                    updatePlaceDetailsComponent(screen, "additional_images", result.data)
                    updatePlaceDetailsComponent(screen, "placeDescription", result.data)
                    updatePlaceDetailsComponent(screen, "place_info_row", result.data)
                    delay(2000)
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

    private fun updatePlaceDetailsComponent(
        screen: Screen,
        componentId: String,
        newItem: InfoItemModel
    ) {
        _screenStateModels.value.map { screenModel ->
            if (screenModel.name == screen) {
                val updatedComponents = updatePlaceDetailsStateModels(componentId, screenModel.components, newItem)
                updateScreenModelByScreenName(screenModel.name, screenModel.copy(components = updatedComponents))
            } else {
                screenModel
            }
        }
    }

    private fun updatePlaceDetailsStateModels(
        componentId: String, components: List<ComponentStateModel>, newItem: InfoItemModel
    ): List<ComponentStateModel> {
        return components.map { component ->
            if (component.dataSource == componentId) {
                when (component) {
                    is ComponentStateModel.ImageSlider -> {
                        component.copy(urlList = newItem.additionalImages)
                    }
                    is ComponentStateModel.Description -> {
                        component.copy(value = newItem.subtitle)
                    }
                    is ComponentStateModel.Info -> {
                        component.copy(infoTitle = newItem.title, leftTag = newItem.leftTag, rightTag = newItem.rightTag)
                    }
                    else -> component
                }
            } else {
                component
            }
        }

    }

    override fun onAction(componentAction: ComponentAction) {
        when (componentAction) {
            is ComponentAction.FetchData -> {

            }
            is ComponentAction.NavigateToRoute -> {
                navigateToRoute(
                    route = componentAction.route,
                    argument = componentAction.param,
                    isPopBack = componentAction.isPopBack,
                    popupBackRoute = componentAction.popupBackRoute
                )
            }
            is ComponentAction.NavigateBack -> {
                navigateBack()
            }
            is ComponentAction.ButtonAction -> {
                when(componentAction.action) {
                    "save_place" -> {

                    }
                }
            }
        }
    }
}