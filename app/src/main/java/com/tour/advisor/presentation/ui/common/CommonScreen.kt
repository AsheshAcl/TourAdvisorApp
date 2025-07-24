package com.tour.advisor.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.ComponentRegistry
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.ui.screens.HomeViewModel

@Composable
fun DynamicScreen(modifier: Modifier = Modifier,
                      components: List<ComponentStateModel>,
                      homeViewModel: HomeViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        val fixedComponents = components.filter { it.type == ComponentConstant.TOP_BAR_COMPONENT_NAME ||
                it.type == ComponentConstant.SPLASH_COMPONENT_NAME}
        fixedComponents.forEach {
            RenderComponent(it, homeViewModel)
        }

        val bodyComponents = components.filterNot { it.type == ComponentConstant.TOP_BAR_COMPONENT_NAME ||
                it.type == ComponentConstant.SPLASH_COMPONENT_NAME }
        LazyColumn(
            modifier = modifier.padding(10.dp), contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(bodyComponents) { component ->
                RenderComponent(component, homeViewModel)
            }
        }
    }
}

@Composable
fun RenderComponent(component: ComponentStateModel, homeViewModel: HomeViewModel) {
    val fqName = ComponentRegistry.components[component.type] ?: return
    fqName.invoke(component, homeViewModel)
}