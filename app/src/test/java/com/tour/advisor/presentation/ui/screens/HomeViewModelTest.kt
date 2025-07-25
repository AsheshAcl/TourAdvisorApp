package com.tour.advisor.presentation.ui.screens

import com.tour.advisor.base.TestCase
import com.tour.advisor.data.screen.model.ScreenConfig
import com.tour.advisor.domain.usecases.PlacesUseCase
import com.tour.advisor.domain.usecases.ScreenConfigUseCase
import com.tour.advisor.logger.LoggerService
import com.tour.advisor.presentation.ui.constants.Screen
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelTest: TestCase() {

    private val screenConfigUseCase: ScreenConfigUseCase = mockk()
    private val placeUseCase: PlacesUseCase = mockk()
    private val logger: LoggerService = mockk(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        coEvery { screenConfigUseCase() } returns listOf(
            ScreenConfig(
                screen_name = Screen.HOME_SCREEN.name,
                screen_title = "Home screen",
                route = Screen.HOME_SCREEN.name,
                next_screen_route = "HOME_SCREEN",
                ui_components = emptyList()
            )
        )

        viewModel = HomeViewModel(screenConfigUseCase, placeUseCase, logger)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `screenStateModels should emit values from screenConfigUseCase`() = runTest {
        advanceUntilIdle()
        val homeScreen = viewModel.homeScreenConfig.first()
        assertNotNull(homeScreen)
        assertEquals(Screen.HOME_SCREEN, homeScreen?.name)
    }

    /*@Test
    fun `getPlaces emits success response and updates screen state`() = runTest {
        val screen = Screen.HOME_SCREEN
        val places = listOf(InfoItemModel(title = "Test Place"))

        coEvery { placeUseCase.getPlaces() } returns Response.Success(places)

        viewModel.getPlaces(screen)

        advanceUntilIdle()

        val screenModel = viewModel.homeScreenConfig.first()
        assertTrue(screenModel?.components?.any {
            (it is ComponentStateModel.HorizontalList && it.items.containsAll(places)) ||
                    (it is ComponentStateModel.VerticalList && it.items.containsAll(places))
        } ?: false)
    }

    @Test
    fun `getPlaces emits error response and shows error`() = runTest {
        val screen = Screen.HOME_SCREEN
        val errorMessage = "Network error"
        coEvery { placeUseCase.getPlaces() } returns Response.Error(errorMessage)

        viewModel.getPlaces(screen)

        advanceUntilIdle()

        val error = viewModel.uiState.first().errorMessage
        assertEquals(errorMessage, error)
    }

    @Test
    fun `callDetailsApi updates multiple components from InfoItemModel`() = runTest {
        val screen = Screen.PLACE_DETAIL_SCREEN
        val placeDetail = InfoItemModel(
            title = "Title",
            subtitle = "Description",
            additionalImages = listOf("url1", "url2"),
            leftTag = "left",
            rightTag = "right"
        )

        coEvery { placeUseCase.getPlaceDetails(any()) } returns Response.Success(placeDetail)

        viewModel.callDetailsApi(screen, "Taj Mahal")
        advanceUntilIdle()

        val screenModel = viewModel.detailsScreenConfig.first()
        val components = screenModel?.components ?: emptyList()

        assertTrue(components.any { it is ComponentStateModel.ImageSlider && it.urlList == placeDetail.additionalImages })
        assertTrue(components.any { it is ComponentStateModel.Description && it.value == placeDetail.subtitle })
        assertTrue(components.any {
            it is ComponentStateModel.Info &&
                    it.infoTitle == placeDetail.title &&
                    it.leftTag == placeDetail.leftTag &&
                    it.rightTag == placeDetail.rightTag
        })
    }*/
}