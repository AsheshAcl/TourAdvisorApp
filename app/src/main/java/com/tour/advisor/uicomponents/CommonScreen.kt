package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.presentation.dynamicUI.components.ApiTextComponent
import com.tour.advisor.presentation.dynamicUI.components.HorizontalListComponent
import com.tour.advisor.presentation.dynamicUI.components.SplashComponent
import com.tour.advisor.presentation.dynamicUI.components.TextComponent
import com.tour.advisor.presentation.dynamicUI.components.TopAppBarComponent
import com.tour.advisor.presentation.dynamicUI.components.UIComponent
import com.tour.advisor.presentation.dynamicUI.components.VerticalListComponent
import com.tour.advisor.presentation.ui.main.HomeViewModel

@Composable
fun CommonScreenRender(modifier: Modifier = Modifier,
                       components: List<UIComponent>,
                       homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        components.forEach { component ->
            when (component) {
                is TopAppBarComponent -> TopAppBar(component)
                is TextComponent -> TextComponent(Modifier.padding(8.dp), component)
                is ApiTextComponent -> ApiTextComponent(Modifier, component)
                is HorizontalListComponent -> HorizontalScrollList(component, homeViewModel = homeViewModel)
                is VerticalListComponent -> VerticalScrollList(component)
                is SplashComponent -> SplashComponent(component)
                else -> {

                }
            }
        }
    }
}