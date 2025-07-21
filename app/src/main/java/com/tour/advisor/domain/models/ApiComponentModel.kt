package com.tour.advisor.domain.models

interface ComponentItemModel

/*data class PlaceItemModel(
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val cost: String?,
    val location: String?,
    val rating: String?
) : ComponentItemModel*/


data class InfoItemModel(
    val title: String?,
    val imageUrl: String?,
    val subtitle: String?,
    val leftTag: String?,
    val rightTag: String?,
    val value: String?,
    val additionalImages: List<String>? = emptyList()
) : ComponentItemModel