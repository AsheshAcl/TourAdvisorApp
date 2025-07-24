package com.tour.advisor.data.screen.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenConfig(
    val screen_name: String,
    val screen_title: String? = null,
    val route: String,
    val ui_components: List<UIComponent>,
    val auto_navigate_after: Long? = 0
)

@Serializable
sealed class UIComponent {
    abstract val id: String
    @SerialName("type")
    abstract val type: String
    @SerialName("data_source")
    val dataSource: String? = null
}

@Serializable
@SerialName("topAppBar")
data class TopAppBarComponent(
    override val id: String,
    override val type: String,
    val title: String,
    val showBack: Boolean? = false,
    val fields: List<UIComponent> = emptyList()
) : UIComponent() {

}

@Serializable
@SerialName("splash")
data class SplashComponent(
    override val id: String,
    override val type: String,
    val fields: List<UIComponent> = emptyList(),
) : UIComponent()

@Serializable
@SerialName("onboarding")
data class OnboardingComponent(
    override val id: String,
    override val type: String,
    val items: List<OnboardingItem> = emptyList()
) : UIComponent()

@Serializable
@SerialName("onboarding_item")
data class OnboardingItem(
    val title: String? = null,
    val description: String? = null,
    val buttonText: String? = null,
    val image: String? = null
)

@Serializable
@SerialName("text")
data class TextComponent(
    override val id: String,
    override val type: String,
    val value: String,
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
@SerialName("image")
data class ImageComponent(
    override val id: String,
    override val type: String,
    val resource: String? = null
) : UIComponent()

@Serializable
@SerialName("button")
data class ButtonComponent(
    override val id: String,
    override val type: String,
    val value: String,
    val style: String,
    val action: String? = null
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
    override val type: String
) : UIComponent()

@Serializable
@SerialName("description")
data class DescriptionComponent(
    override val id: String,
    override val type: String,
    val section_header: String,
    val section_header_style: String
) : UIComponent()

@Serializable
@SerialName("horizontal_scroll_list")
data class HorizontalListComponent(
    override val id: String,
    override val type: String,
    val section_header: String,
    val section_header_style: String,
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
    val fields: List<UIComponent> = emptyList(),
    val onClickRoute: String
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
@SerialName("info_row")
data class InfoRowComponent(
    override val id: String,
    override val type: String
) : UIComponent()

@SerialName("rating_single")
@Serializable
data class SingleRatingComponent(
    override val id: String,
    override val type: String,
    val resource: String? = null
) : UIComponent()
