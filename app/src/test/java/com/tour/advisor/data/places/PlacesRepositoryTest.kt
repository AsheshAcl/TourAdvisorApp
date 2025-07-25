package com.tour.advisor.data.places

import com.tour.advisor.base.TestCase
import com.tour.advisor.data.places.model.PlaceModel
import com.tour.advisor.domain.mapper.DataMapper.Companion.toDomain
import com.tour.advisor.domain.result.Response
import com.tour.advisor.logger.LoggerService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlacesRepositoryTest: TestCase() {

    private val remoteSource: DataRemoteDataSource = mockk()
    private val dao: PlaceDao = mockk(relaxed = true)
    private val logger: LoggerService = mockk(relaxed = true)

    private lateinit var repositoryImpl: PlacesRepositoryImpl
    @BeforeEach
    fun setUp() {
        repositoryImpl = PlacesRepositoryImpl(remoteSource, dao, logger)
    }
    @Test
    fun `returns remote data when available`() = runTest {
        val remoteData = listOf(
            PlaceModel("Place A", "img.png", "desc", "100", "India", 4.5, listOf())
        )
        coEvery { remoteSource.getRemotePlacesData() } returns remoteData
        val result = repositoryImpl.getPlaces()
        assertTrue(result is Response.Success)
        assertEquals(remoteData, (result as Response.Success).data)
    }

    @Test
    fun `returns local data when remote is empty`() = runTest {
        coEvery { remoteSource.getRemotePlacesData() } returns emptyList()
        val localEntities = listOf(
            PlaceEntity("Local Place", "img", "desc", "200", "Location", 3.5, null)
        )
        val domainModels = localEntities.toDomain()
        coEvery { dao.getPlaces() } returns localEntities
        val result = repositoryImpl.getPlaces()
        assertTrue(result is Response.Success)
        assertEquals(domainModels, (result as Response.Success).data)
    }

    @Test
    fun `returns local fallback data when remote throws exception`() = runTest {
        coEvery { remoteSource.getRemotePlacesData() } throws RuntimeException("API failed")
        val localEntities = listOf(
            PlaceEntity("Local Place", "img", "desc", "200", "Location", 3.5, null)
        )
        val domainModels = localEntities.toDomain()
        coEvery { dao.getPlaces() } returns localEntities
        val result = repositoryImpl.getPlaces()
        assertTrue(result is Response.Success)
        assertEquals(domainModels, (result as Response.Success).data)
    }

    @Test
    fun `returns error when both remote and local fail`() = runTest {
        coEvery { remoteSource.getRemotePlacesData() } throws RuntimeException("API failed")
        coEvery { dao.getPlaces() } throws RuntimeException("DB failed")
        val result = repositoryImpl.getPlaces()
        assertTrue(result is Response.Error)
        assertEquals("DB fallback", (result as Response.Error).errorMessage)
    }

    @Test
    fun `getPlaceDetails should return place model when DAO returns entity`() = runTest {
        val placeName = "Taj Mahal"
        val localEntity = PlaceEntity(
            placeName = placeName,
            placeImage = "image.jpg",
            placeDescription = "Beautiful place",
            placeCost = "Free",
            placeLocation = "Agra",
            placeRating = 4.8,
            additionalImages = listOf("img1.jpg", "img2.jpg")
        )
        coEvery { dao.getPlaceDetails(placeName) } returns localEntity
        val result = repositoryImpl.getPlaceDetails(placeName)
        assertTrue(result is Response.Success)
        val model = (result as Response.Success).data
        assertEquals(placeName, model.placeName)
        assertEquals("Agra", model.placeLocation)
    }
}