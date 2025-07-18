package com.tour.advisor.domain.models

import com.tour.advisor.presentation.ui.main.constants.Screen

data class ScreenModels(
    val name: Screen?,
    val title: String,
    val components: List<ComponentStateModel>,
    val autoNavigateAfter: Long? = null
)

sealed class ComponentStateModel {
    val id: String = ""
    abstract val dataSource: String?

    data class Splash(
        override val dataSource: String? = null,
        val components: List<ComponentStateModel>
    ) : ComponentStateModel()

    data class TopBar(
        override val dataSource: String? = null,
        val title: String,
        val showBack: Boolean? = false
    ) : ComponentStateModel()

    data class Text(
        override val dataSource: String? = null,
        val value: String,
        val style: String?
    ) : ComponentStateModel()

    data class Button(
        override val dataSource: String? = null,
        val value: String,
        val style: String,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class Image(
        override val dataSource: String? = null,
        val url: String? = null
    ) : ComponentStateModel()

    data class ImageLocal(
        override val dataSource: String? = null,
        val resource: String? = null
    ) : ComponentStateModel()

    data class HorizontalList(
        override val dataSource: String? = null,
        val sectionHeader: String,
        val sectionHeaderStyle: String,
        val fields: List<ComponentStateModel>,
        var items: List<ComponentItemModel>,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class LongCard(
        override val dataSource: String? = null,
        val value: String?
    ) : ComponentStateModel()

    data class VerticalList(
        override val dataSource: String? = null,
        val sectionHeader: String,
        val sectionHeaderStyle: String,
        val fields: List<ComponentStateModel>,
        var items: List<ComponentItemModel>,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class SmallCard(
        override val dataSource: String? = null,
        val value: String?
    ) : ComponentStateModel()

    data class Unknown(
        override val dataSource: String? = null,
    ) : ComponentStateModel()
}