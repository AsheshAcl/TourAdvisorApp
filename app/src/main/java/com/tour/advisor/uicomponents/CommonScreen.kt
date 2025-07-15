package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tour.advisor.dynamicUI.components.ApiTextComponent
import com.tour.advisor.dynamicUI.components.HorizontalListComponent
import com.tour.advisor.dynamicUI.components.SplashComponent
import com.tour.advisor.dynamicUI.components.TextComponent
import com.tour.advisor.dynamicUI.components.TopAppBarComponent
import com.tour.advisor.dynamicUI.components.UIComponent
import com.tour.advisor.dynamicUI.components.VerticalListComponent
import com.tour.advisor.ui.TopAppBar
import com.tour.advisor.ui.main.HomeViewModel

@Composable
fun CommonScreenRender(modifier: Modifier = Modifier,
                       components: List<UIComponent>,
                       homeViewModel: HomeViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        components.forEach { component ->
            when (component) {
                is TopAppBarComponent -> TopAppBar(component)
                is TextComponent -> TextComponent(component)
                is ApiTextComponent -> ApiTextComponent(component)
                is HorizontalListComponent -> HorizontalScrollList(component, homeViewModel = homeViewModel)
                is VerticalListComponent -> VerticalScrollList(component)
                is SplashComponent -> SplashComponent(component)
                else -> {

                }
            }
        }
    }
}