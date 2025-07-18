package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.ui.main.HomeViewModel

@Composable
fun CommonScreenRender(
    modifier: Modifier = Modifier,
    components: List<ComponentStateModel>,
    homeViewModel: HomeViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        components.forEach { component ->
            when (component) {
                is ComponentStateModel.TopBar -> TopAppBar(component)
                is ComponentStateModel.Splash -> SplashComponent(component)
                else -> {

                }
            }
        }
        LazyColumn(
            modifier = modifier.padding(10.dp), contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(components) { component ->
                when (component) {
                    is ComponentStateModel.Text -> TextComponent(Modifier.padding(8.dp), component)
                    is ComponentStateModel.Button -> ButtonComponent(Modifier, component)

                    is ComponentStateModel.HorizontalList -> HorizontalScrollList(
                        component, homeViewModel = homeViewModel
                    )

                    is ComponentStateModel.VerticalList -> VerticalScrollList(
                        component, homeViewModel = homeViewModel
                    )

                    /*is ComponentStateModel.LongCard -> LongCardImage(ComponentStateModel.Image())
                    is ComponentStateModel.SmallCard -> SmallCardImage(ComponentStateModel.Image())*/
                    else -> {

                    }
                }
            }
        }
    }
}