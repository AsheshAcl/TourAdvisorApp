package com.tour.advisor.uicomponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tour.advisor.domain.models.ComponentStateModel

@Composable
fun LongCardImage(image: ComponentStateModel.Image) {
    Card(
        /*onClick = {
            homeViewModel.navigateToRoute(component.onClickRoute)
        },*/
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = image.url,
            contentDescription = "Card Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun LongCardImagePreview() {
    LongCardImage(ComponentStateModel.Image())
}