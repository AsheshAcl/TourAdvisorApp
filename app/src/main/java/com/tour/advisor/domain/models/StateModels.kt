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

    data class Splash(
        val components: List<ComponentStateModel>
    ) : ComponentStateModel()

    data class TopBar(
        val title: String
    ) : ComponentStateModel()

    data class Text(
        val value: String,
        val style: String?
    ) : ComponentStateModel()

    data class ApiText(
        val value: String,
        val style: String?
    ) : ComponentStateModel()

    data class Button(
        val text: String,
        val style: String,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class Image(
        val url: String?
    ) : ComponentStateModel()

    data class HorizontalList(
        val sectionHeader: String,
        val sectionHeaderStyle: String,
        val fields: List<ComponentStateModel>,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class LongCard(
        val value: String?
    ) : ComponentStateModel()

    data class VerticalList(
        val sectionHeader: String,
        val sectionHeaderStyle: String,
        val fields: List<ComponentStateModel>,
        val onClickRoute: String
    ) : ComponentStateModel()

    data class SmallCard(
        val value: String?
    ) : ComponentStateModel()

    data class Unknown(
        val value: String
    ) : ComponentStateModel()
}