package com.tour.advisor.presentation.dynamicUI.action


sealed class ComponentAction {
    data class NavigateToRoute(val route: String, val param: String) : ComponentAction()
    data object NavigateBack : ComponentAction()
    data class FetchData(val dataSource: String) : ComponentAction()
    data class ButtonAction(val action: String) : ComponentAction()
}