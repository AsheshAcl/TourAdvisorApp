package com.tour.advisor.presentation.ui.state

import com.tour.advisor.domain.result.Response

data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)