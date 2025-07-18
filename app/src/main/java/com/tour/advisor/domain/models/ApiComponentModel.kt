package com.tour.advisor.domain.models

interface ComponentItemModel

data class PlaceItemModel(
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val cost: String?,
    val rating: Double?
) : ComponentItemModel