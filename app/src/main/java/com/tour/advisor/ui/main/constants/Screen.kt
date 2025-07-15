package com.tour.advisor.ui.main.constants

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Screen(name: String) {
    @SerialName("SPLASH_SCREEN")
    SPLASH_SCREEN("SPLASH_SCREEN"),
    @SerialName("ONBOARDING_SCREEN")
    ONBOARDING_SCREEN("ONBOARDING_SCREEN"),
    @SerialName("HOME_SCREEN")
    HOME_SCREEN("HOME_SCREEN"),
    @SerialName("PLACE_DETAIL_SCREEN")
    PLACE_DETAILS_SCREEN("PLACE_DETAIL_SCREEN")
}