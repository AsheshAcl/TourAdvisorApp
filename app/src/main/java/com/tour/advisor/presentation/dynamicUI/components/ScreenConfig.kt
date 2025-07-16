package com.tour.advisor.presentation.dynamicUI.components

import com.tour.advisor.presentation.ui.main.constants.Screen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenConfig(
    val screen_name: Screen,
    val screen_title: String = "",
    val route: String,
    val ui_components: List<UIComponent>,
    val auto_navigate_after: Long = 0,
//    val next_route: String? = null
)

@Serializable
sealed class UIComponent {
    abstract val id: String
    abstract val type: String
}

@Serializable
@SerialName("topAppBar")
data class TopAppBarComponent(
    override val id: String,
    override val type: String,
    val title: String,
    val fields: List<UIComponent> = emptyList()
) : UIComponent()

@Serializable
@SerialName("splash")
data class SplashComponent(
    override val id: String,
    override val type: String,
    val fields: List<UIComponent> = emptyList()
) : UIComponent()

@Serializable
@SerialName("onboarding")
data class OnboardingComponent(
    override val id: String,
    override val type: String,
    val fields: List<UIComponent> = emptyList()
) : UIComponent()

@Serializable
@SerialName("longCardImage")
data class LongCardImageComponent(
    override val id: String,
    override val type: String,
    val resource: String? = null
) : UIComponent()

@Serializable
@SerialName("smallCardImage")
data class SmallCardImageComponent(
    override val id: String,
    override val type: String,
    val resource: String? = null
) : UIComponent()

@Serializable
@SerialName("text")
data class TextComponent(
    override val id: String,
    override val type: String,
    val value: String? = null,
    val style: String? = null
) : UIComponent()

@Serializable
@SerialName("apiText")
data class ApiTextComponent(
    override val id: String,
    override val type: String,
    val value: String? = null,
    val style: String? = null
) : UIComponent()

@Serializable
@SerialName("icon")
data class IconComponent(
    override val id: String,
    override val type: String,
    val resource: String
) : UIComponent()

@Serializable
@SerialName("button")
data class ButtonComponent(
    override val id: String,
    override val type: String,
    val text: String,
    val style: String,
    val onClickRoute: String
) : UIComponent()

@Serializable
@SerialName("loading_indicator")
data class LoadingIndicatorComponent(
    override val id: String,
    override val type: String
) : UIComponent()

@Serializable
@SerialName("image_slider")
data class ImageSliderComponent(
    override val id: String,
    override val type: String,
    val resources: List<String>
) : UIComponent()

@Serializable
@SerialName("horizontal_scroll_list")
data class HorizontalListComponent(
    override val id: String,
    override val type: String,
    val section_header: String,
    val section_header_style: String,
    val data_source: String,
    val fields: List<UIComponent> = emptyList(),
    val onClickRoute: String
) : UIComponent()

@Serializable
@SerialName("vertical_scroll_list")
data class VerticalListComponent(
    override val id: String,
    override val type: String,
    val section_header: String,
    val section_header_style: String,
    val data_source: String,
    val fields: List<UIComponent> = emptyList()
) : UIComponent()

@Serializable
@SerialName("image")
data class ImageComponent(
    override val id: String,
    override val type: String,
    val style: String,
    val resource: String? = null,
    val onClickRoute: String? = null
) : UIComponent()

@Serializable
@SerialName("info_row")
data class InfoRowComponent(
    override val id: String,
    override val type: String,
    val items: List<InfoRowItem>
) : UIComponent()

@SerialName("rating_single")
@Serializable
data class SingleRatingComponent(
    override val id: String,
    override val type: String,
    val resource: String? = null
) : UIComponent()

@Serializable
data class InfoRowItem(
    val id: String,
    val type: String,
    val style: String? = null,
    val resource: String? = null
)
