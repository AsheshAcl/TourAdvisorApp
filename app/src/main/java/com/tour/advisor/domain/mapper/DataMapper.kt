package com.tour.advisor.domain.mapper

import com.tour.advisor.data.screen.parser.model.ApiTextComponent
import com.tour.advisor.data.screen.parser.model.HorizontalListComponent
import com.tour.advisor.data.screen.parser.model.ImageComponent
import com.tour.advisor.data.screen.parser.model.LongCardImageComponent
import com.tour.advisor.data.screen.parser.model.ScreenConfig
import com.tour.advisor.data.screen.parser.model.SmallCardImageComponent
import com.tour.advisor.data.screen.parser.model.SplashComponent
import com.tour.advisor.data.screen.parser.model.TextComponent
import com.tour.advisor.data.screen.parser.model.TopAppBarComponent
import com.tour.advisor.data.screen.parser.model.UIComponent
import com.tour.advisor.data.screen.parser.model.VerticalListComponent
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
                is TopAppBarComponent -> ComponentStateModel.TopBar(title = title)
                is SplashComponent -> ComponentStateModel.Splash(components = fields.map { it.toUiComponent() })
                is TextComponent -> ComponentStateModel.Text(value = value, style = style)
                is ApiTextComponent -> ComponentStateModel.ApiText(value = value, style = style)
                is ImageComponent -> ComponentStateModel.Image(
                    url = resource
                )
                is HorizontalListComponent -> ComponentStateModel.HorizontalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    onClickRoute = onClickRoute
                )
                is VerticalListComponent -> ComponentStateModel.VerticalList(
                    sectionHeader = section_header,
                    sectionHeaderStyle = section_header_style,
                    fields = fields.map { it.toUiComponent() },
                    onClickRoute = onClickRoute
                )

                is LongCardImageComponent -> ComponentStateModel.LongCard(
                    value = resource
                )

                is SmallCardImageComponent -> ComponentStateModel.SmallCard(
                    value = resource
                )

                else -> ComponentStateModel.Unknown("")
            }
        }
    }
}