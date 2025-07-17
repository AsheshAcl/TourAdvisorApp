package com.tour.advisor.presentation.dynamicUI.mapper

import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.models.ComponentItemModel
import com.tour.advisor.domain.models.PlaceItemModel

class ComponentsMapper {
    companion object {
        fun PlaceModel.toComponentItem(): ComponentItemModel {
            return PlaceItemModel(
                title = placeName,
                imageUrl = placeImage,
                description = placeDescription,
                cost = placeCost,
                rating = placeRating
            )
        }
    }
}