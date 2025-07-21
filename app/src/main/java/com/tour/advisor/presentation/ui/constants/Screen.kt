package com.tour.advisor.presentation.ui.constants

import kotlinx.serialization.Serializable

@Serializable
enum class Screen(name: String) {
    SPLASH_SCREEN("SPLASH_SCREEN"),
    ONBOARDING_SCREEN("ONBOARDING_SCREEN"),
    HOME_SCREEN("HOME_SCREEN"),
    PLACE_DETAIL_SCREEN("PLACE_DETAIL_SCREEN");

    companion object {
        fun fromValue(value: String): Screen? {
            return entries.find { it.name == value }
        }
    }
}