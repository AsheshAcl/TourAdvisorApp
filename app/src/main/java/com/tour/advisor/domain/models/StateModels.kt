package com.tour.advisor.domain.models

import com.tour.advisor.presentation.ui.constants.Screen

data class ScreenModels(
    val name: Screen?,
    val title: String?,
    val route: String?,
    val nextScreenRoute: String?,
    val arguments: List<String>?,
    val components: List<ComponentStateModel>,
    val autoNavigateAfter: Long? = null
)

sealed class ComponentStateModel {
    val id: String = ""
    abstract val dataSource: String?
    abstract val type: String?

    data class Splash(
        override val dataSource: String? = null,
        override val type: String? = null,
        val components: List<ComponentStateModel>,
    ) : ComponentStateModel()

    data class Onboarding(
        override val dataSource: String? = null,
        override val type: String? = null,
        val items: List<OnboardingItemState>,
    ) : ComponentStateModel()

    data class OnboardingItemState(
        val dataSource: String? = null,
        val title: String? = null,
        val description: String? = null,
        val buttonText: String? = null,
        val image: String? = null
    )

    data class TopBar(
        override val dataSource: String? = null,
        override val type: String? = null,
        val title: String,
        val showBack: Boolean? = false
    ) : ComponentStateModel()

    data class Text(
        override val dataSource: String? = null,
        override val type: String? = null,
        val value: String,
        val style: String?
    ) : ComponentStateModel()

    data class Button(
        override val dataSource: String? = null,
        override val type: String? = null,
        val value: String,
        val style: String,
        val action: String? = null
    ) : ComponentStateModel()

    data class Image(
        override val dataSource: String? = null,
        override val type: String? = null,
        val url: String? = null
    ) : ComponentStateModel()

    data class ImageLocal(
        override val dataSource: String? = null,
        override val type: String? = null,
        val resource: String? = null
    ) : ComponentStateModel()

    interface SectionBaseComponent {
        val sectionHeader: String
        val sectionHeaderStyle: String
    }
    sealed class BaseListComponent : ComponentStateModel(), SectionBaseComponent {
        abstract val fields: List<ComponentStateModel>
        abstract var items: List<ComponentItemModel>
        abstract val onClickRoute: String
    }

    data class HorizontalList(
        override val dataSource: String? = null,
        override val type: String? = null,
        override val sectionHeader: String,
        override val sectionHeaderStyle: String,
        override val fields: List<ComponentStateModel>,
        override var items: List<ComponentItemModel>,
        override val onClickRoute: String
    ) : BaseListComponent()

    data class LongCard(
        override val dataSource: String? = null,
        override val type: String? = null,
        val value: String?
    ) : ComponentStateModel()

    data class VerticalList(
        override val dataSource: String? = null,
        override val type: String? = null,
        override val sectionHeader: String,
        override val sectionHeaderStyle: String,
        override val fields: List<ComponentStateModel>,
        override var items: List<ComponentItemModel>,
        override val onClickRoute: String
    ) : BaseListComponent()

    data class SmallCard(
        override val dataSource: String? = null,
        override val type: String? = null,
        val value: String?
    ) : ComponentStateModel()

    data class ImageSlider(
        override val dataSource: String? = null,
        override val type: String? = null,
        val urlList: List<String>? = emptyList(),
    ) : ComponentStateModel()

    data class Description(
        override val dataSource: String? = null,
        override val type: String? = null,
        override val sectionHeader: String,
        override val sectionHeaderStyle: String,
        val value: String? = null
    ) : ComponentStateModel(), SectionBaseComponent

    data class Info(
        override val dataSource: String? = null,
        override val type: String? = null,
        val infoTitle: String? = null,
        val leftTag: String? = null,
        val rightTag: String? = null
    ) : ComponentStateModel()

    data class Unknown(
        override val dataSource: String? = null,
        override val type: String? = null,
    ) : ComponentStateModel()
}