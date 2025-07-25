package com.tour.advisor.data.places.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlaceModelTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `should serialize PlaceModel to JSON`() {
        val place = PlaceModel(
            placeName = "Taj Mahal",
            placeImage = "taj.jpg",
            placeDescription = "Famous monument",
            placeCost = "500",
            placeLocation = "Agra",
            placeRating = 4.8,
            additionalImages = listOf("taj1.jpg", "taj2.jpg")
        )

        val result = json.encodeToString(place)

        assertTrue(result.contains("\"place_name\":\"Taj Mahal\""))
        assertTrue(result.contains("\"place_image\":\"taj.jpg\""))
        assertTrue(result.contains("\"place_rating\":4.8"))
    }

    @Test
    fun `should deserialize JSON to PlaceModel`() {
        val jsonInput = """
            {
                "place_name": "Eiffel Tower",
                "place_image": "eiffel.jpg",
                "place_description": "Landmark in Paris",
                "place_cost": "300",
                "place_location": "Paris",
                "place_rating": 4.5,
                "additional_images": ["img1.jpg", "img2.jpg"]
            }
        """.trimIndent()

        val place = json.decodeFromString<PlaceModel>(jsonInput)

        assertEquals("Eiffel Tower", place.placeName)
        assertEquals("eiffel.jpg", place.placeImage)
        assertEquals("Landmark in Paris", place.placeDescription)
        assertEquals("300", place.placeCost)
        assertEquals("Paris", place.placeLocation)
        assertEquals(4.5, place.placeRating)
        assertEquals(listOf("img1.jpg", "img2.jpg"), place.additionalImages)
    }

    @Test
    fun `should handle nullable and missing fields`() {
        val jsonInput = """
            {
                "place_name": "Unknown Place"
            }
        """.trimIndent()

        val place = json.decodeFromString<PlaceModel>(jsonInput)

        assertEquals("Unknown Place", place.placeName)
        assertNull(place.placeImage)
        assertNull(place.placeDescription)
        assertNull(place.placeCost)
        assertNull(place.placeLocation)
        assertNull(place.placeRating)
        assertNull(place.additionalImages)
    }
}
