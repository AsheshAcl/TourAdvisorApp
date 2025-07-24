package com.tour.advisor.presentation.ui.state

data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)