package com.tour.advisor.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tour.advisor.domain.models.ComponentStateModel
import kotlinx.coroutines.delay

@Composable
fun ImageSliderComponent(component: ComponentStateModel.ImageSlider, autoSlideDuration: Long = 3000L) {
    if(component.urlList.isNullOrEmpty()) {
        return
    }

    val pagerState = rememberPagerState(pageCount = { component.urlList.size })
    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        val nextPage = (pagerState.currentPage + 1) % component.urlList.size
        pagerState.animateScrollToPage(nextPage)
    }

    Column {
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->

            ImageComponent(
                modifier = Modifier.fillMaxSize(),
                ComponentStateModel.Image(url = component.urlList[page]),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            repeat(component.urlList.size) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (pagerState.currentPage == index) Color.White else Color.LightGray
                        )
                        .padding(horizontal = 2.dp)
                )
                if (index != component.urlList.lastIndex) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}


@Preview
@Composable
private fun ImageSliderComponentPreview() {
    ImageSliderComponent(component = ComponentStateModel.ImageSlider(
        urlList = listOf(
            "https://ychef.files.bbci.co.uk/624x351/p08m61wb.jpg",
            "https://caminoincamachupicchu.org/cmingutd/wp-content/uploads/2021/06/machu-picchu-llama.jpg",
            "https://static.wixstatic.com/media/c8ce33_0b04ed009319494c902b146dc9baf028~mv2.jpg/v1/fit/w_1281,h_701,q_90,enc_avif,quality_auto/c8ce33_0b04ed009319494c902b146dc9baf028~mv2.jpg"
        )
    ))
}

