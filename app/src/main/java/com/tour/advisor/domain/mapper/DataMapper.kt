package com.tour.advisor.domain.mapper

import com.tour.advisor.data.places.PlaceEntity
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.data.screen.model.ButtonComponent
import com.tour.advisor.data.screen.model.HorizontalListComponent
import com.tour.advisor.data.screen.model.ImageComponent
import com.tour.advisor.data.screen.model.LongCardImageComponent
import com.tour.advisor.data.screen.model.ScreenConfig
import com.tour.advisor.data.screen.model.SmallCardImageComponent
import com.tour.advisor.data.screen.model.SplashComponent
import com.tour.advisor.data.screen.model.TextComponent
import com.tour.advisor.data.screen.model.TopAppBarComponent
import com.tour.advisor.data.screen.model.UIComponent
import com.tour.advisor.data.screen.model.VerticalListComponent
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.domain.models.ScreenModels
import com.tour.advisor.presentation.ui.main.constants.Screen

class DataMapper {
    companion object {
        fun ScreenConfig.toUi(): ScreenModels {
            return ScreenModels(name = Screen.fromValue(screen_name),
                title = screen_title,
                autoNavigateAfter = auto_navigate_after,
                components = ui_components.map { it.toUiComponent() })
        }

        private fun UIComponent.toUiComponent(): ComponentStateModel {
            return when (this) {
                is TopAppBarComponent -> ComponentStateModel.TopBar(title = title, showBack = showBack)
                is SplashComponent -> ComponentStateModel.Splash(components = fields.map { it.toUiComponent() })
                is TextComponent -> ComponentStateModel.Text(value = value, style = style, dataSource = dataSource)
                is ButtonComponent -> ComponentStateModel.Button(value = value, style = style, onClickRoute = onClickRoute)
                is ImageComponent -> ComponentStateModel.Image(
                    url = resource, dataSource = dataSource
                )
                is HorizontalListComponent -> ComponentStateModel.HorizontalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    items = emptyList(),
                    onClickRoute = onClickRoute,
                    dataSource = dataSource
                )
                is VerticalListComponent -> ComponentStateModel.VerticalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    onClickRoute = onClickRoute,
                    items = emptyList(),
                    dataSource = dataSource
                )

                is LongCardImageComponent -> ComponentStateModel.LongCard(
                    value = resource,
                    dataSource = dataSource
                )

                is SmallCardImageComponent -> ComponentStateModel.SmallCard(
                    value = resource,
                    dataSource = dataSource
                )

                else -> ComponentStateModel.Unknown("")
            }
        }

        fun PlaceEntity.toDomain(): PlaceModel = PlaceModel(
            placeName = placeName,
            placeImage = placeImage,
            placeDescription = placeDescription,
            placeCost = placeCost,
            placeRating = placeRating
        )

        fun List<PlaceEntity>.toDomain(): List<PlaceModel> = map { it.toDomain() }
    }
}