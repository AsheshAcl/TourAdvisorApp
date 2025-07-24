package com.tour.advisor.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tour.advisor.domain.models.ComponentStateModel
import com.tour.advisor.navigation.Route
import com.tour.advisor.presentation.dynamicUI.action.ComponentAction
import com.tour.advisor.presentation.dynamicUI.action.ComponentActionHandler
import com.tour.advisor.presentation.ui.constants.ComponentConstant
import com.tour.advisor.presentation.ui.screens.OnBoardingScreen
import com.tour.annotations.Component
import kotlinx.coroutines.launch

@Composable
@Component(ComponentConstant.ONBOARDING_COMPONENT_NAME)
fun OnboardingComponent(
    componentModel: ComponentStateModel,
    actionHandler: ComponentActionHandler
) {
    val component = componentModel as? ComponentStateModel.Onboarding ?: return
    val pages: List<ComponentStateModel.OnboardingItemState> = component.items
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageItem(pages[page])
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (pagerState.currentPage == pages.lastIndex) {
                        actionHandler.onAction(
                            ComponentAction.NavigateToRoute(
                                route = Route.HOME_SCREEN.route,
                                isPopBack = true,
                                popupBackRoute = Route.ONBOARDING_SCREEN.route
                            )
                        )
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                pages[pagerState.currentPage].buttonText?.let {
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
fun OnboardingPageItem(page: ComponentStateModel.OnboardingItemState) {
    Box(modifier = Modifier.fillMaxSize()) {
        ImageComponent(
            modifier = Modifier.fillMaxSize(),
            ComponentStateModel.ImageLocal(resource = page.image)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .background(Color.Black.copy(alpha = 0.4f), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            page.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            page.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingScreenPreview() {
    OnBoardingScreen(
        modifier = Modifier
    )
}

