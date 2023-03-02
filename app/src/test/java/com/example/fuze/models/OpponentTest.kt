package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class OpponentTest {

    @Test
    fun testOpponent() {
        val opponent = Opponent(
            acronym = "OPP",
            id = 1L,
            image_url = "https://example.com/image.png",
            location = "Example Location",
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example Opponent",
            slug = "example-opponent"
        )
        assertEquals("OPP", opponent.acronym)
        assertEquals(1L, opponent.id)
        assertEquals("https://example.com/image.png", opponent.image_url)
        assertEquals("Example Location", opponent.location)
        assertEquals("2022-01-01T12:00:00Z", opponent.modified_at)
        assertEquals("Example Opponent", opponent.name)
        assertEquals("example-opponent", opponent.slug)
    }
}
