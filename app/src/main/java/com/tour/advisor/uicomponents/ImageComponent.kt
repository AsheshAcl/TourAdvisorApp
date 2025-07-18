package com.tour.advisor.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.presentation.utility.UIUtils.Companion.getDrawableIdFromName

@Composable
fun ImageComponent(modifier: Modifier, component: ComponentStateModel.Image, contentScale: ContentScale = ContentScale.Fit) {
    component.url?.let {
        AsyncImage(
            model = it,
            contentDescription = "Image",
            contentScale = contentScale,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ImageComponent(modifier: Modifier, component: ComponentStateModel.ImageLocal, contentScale: ContentScale = ContentScale.Fit) {
    component.resource?.getDrawableIdFromName()?.let { painterResource(id = it) }?.let {
        Image(
            painter = it, contentDescription = null, modifier = modifier/*.size(64.dp)*/,
            contentScale = contentScale
        )
    }
}


@Preview
@Composable
private fun IconComponentPreview() {
    ImageComponent(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        ComponentStateModel.Image(url = "https://ychef.files.bbci.co.uk/624x351/p08m61wb.jpg")
    )
}

@Preview
@Composable
private fun IconLocalComponentPreview() {
    ImageComponent(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        ComponentStateModel.ImageLocal(resource = "ic_app_icon")
    )
}