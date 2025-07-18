package com.tour.advisor.navigation

enum class Route(val route: String) {
    SPLASH_SCREEN("SPLASH_SCREEN"),
    ONBOARDING_SCREEN("ONBOARDING_SCREEN"),
    HOME_SCREEN("HOME_SCREEN"),
    DETAILS_SCREEN("DETAILS_SCREEN/{placeName}"),
}

object NavArgument {
    const val PLACE_NAME = "placeName"
}