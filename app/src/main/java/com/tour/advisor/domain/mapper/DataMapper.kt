package com.tour.advisor.domain.mapper

import com.tour.advisor.data.places.PlaceEntity
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.data.screen.model.ButtonComponent
import com.tour.advisor.data.screen.model.DescriptionComponent
import com.tour.advisor.data.screen.model.HorizontalListComponent
import com.tour.advisor.data.screen.model.ImageComponent
import com.tour.advisor.data.screen.model.ImageSliderComponent
import com.tour.advisor.data.screen.model.InfoRowComponent
import com.tour.advisor.data.screen.model.LongCardImageComponent
import com.tour.advisor.data.screen.model.ScreenConfig
import com.tour.advisor.data.screen.model.SmallCardImageComponent
import com.tour.advisor.data.screen.model.SplashComponent
import com.tour.advisor.data.screen.model.TextComponent
import com.tour.advisor.data.screen.model.TopAppBarComponent
import com.tour.advisor.data.screen.model.UIComponent
import com.tour.advisor.data.screen.model.VerticalListComponent
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.InfoItemModel
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.presentation.ui.constants.Screen

class DataMapper {
    companion object {
        fun ScreenConfig.toUi(): ScreenModels {
            return ScreenModels(name = Screen.fromValue(screen_name),
                title = screen_title,
                autoNavigateAfter = auto_navigate_after,
                components = ui_components.map { it.toUiComponent() })
        }

        fun UIComponent.toUiComponent(): ComponentStateModel {
            return when (this) {
                is TopAppBarComponent -> ComponentStateModel.TopBar(title = title, showBack = showBack, type = type)
                is SplashComponent -> ComponentStateModel.Splash(components = fields.map { it.toUiComponent() }, type = type)
                is TextComponent -> ComponentStateModel.Text(value = value, style = style, dataSource = dataSource, type = type)
                is ButtonComponent -> ComponentStateModel.Button(value = value, style = style, onClickRoute = onClickRoute, type = type)
                is ImageComponent -> ComponentStateModel.Image(
                    url = resource, dataSource = dataSource, type = type
                )
                is HorizontalListComponent -> ComponentStateModel.HorizontalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    items = emptyList(),
                    onClickRoute = onClickRoute,
                    dataSource = dataSource,
                    type = type
                )
                is VerticalListComponent -> ComponentStateModel.VerticalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    onClickRoute = onClickRoute,
                    items = emptyList(),
                    dataSource = dataSource,
                    type = type
                )

                is LongCardImageComponent -> ComponentStateModel.LongCard(
                    value = resource,
                    dataSource = dataSource,
                    type = type
                )

                is SmallCardImageComponent -> ComponentStateModel.SmallCard(
                    value = resource,
                    dataSource = dataSource,
                    type = type
                )

                is DescriptionComponent -> ComponentStateModel.Description(
                    dataSource = dataSource,
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    type = type
                )

                is ImageSliderComponent -> ComponentStateModel.ImageSlider(
                    dataSource = dataSource,
                    type = type
                )

                is InfoRowComponent -> ComponentStateModel.Info(
                    dataSource = dataSource,
                    type = type
                )

                else -> ComponentStateModel.Unknown("")
            }
        }

        fun PlaceEntity.toDomain(): PlaceModel = PlaceModel(
            placeName = placeName,
            placeImage = placeImage,
            placeDescription = placeDescription,
            placeCost = placeCost,
            placeLocation = placeLocation,
            placeRating = placeRating,
            additionalImages = additionalImages
        )

        fun List<PlaceEntity>.toDomain(): List<PlaceModel> = map { it.toDomain() }

        fun PlaceModel.toComponentItem(): InfoItemModel {
            return InfoItemModel(
                title = placeName,
                imageUrl = placeImage,
                subtitle = placeDescription,
                value = placeRating.toString(),
                leftTag = placeLocation,
                rightTag = placeCost,
                additionalImages = listOfNotNull(placeImage) + (additionalImages ?: emptyList())
            )
        }
    }
}