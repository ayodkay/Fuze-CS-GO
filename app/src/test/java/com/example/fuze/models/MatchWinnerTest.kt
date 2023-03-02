package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class MatchWinnerTest {

    @Test
    fun testMatchWinner() {
        val matchWinner = MatchWinner(
            acronym = null,
            id = 1,
            image_url = "https://example.com/image.png",
            location = "Example Location",
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example Match Winner",
            slug = "example-match-winner"
        )
        assertEquals(null, matchWinner.acronym)
        assertEquals(1, matchWinner.id)
        assertEquals("https://example.com/image.png", matchWinner.image_url)
        assertEquals("Example Location", matchWinner.location)
        assertEquals("2022-01-01T12:00:00Z", matchWinner.modified_at)
        assertEquals("Example Match Winner", matchWinner.name)
        assertEquals("example-match-winner", matchWinner.slug)
    }
}
