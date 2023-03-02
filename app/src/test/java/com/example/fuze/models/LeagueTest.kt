package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class LeagueTest {

    @Test
    fun testLeague() {
        val league = League(
            id = 1,
            image_url = "https://example.com/image.png",
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example League",
            slug = "example-league",
            url = "https://example.com/leagues/example-league"
        )
        assertEquals(1, league.id)
        assertEquals("https://example.com/image.png", league.image_url)
        assertEquals("2022-01-01T12:00:00Z", league.modified_at)
        assertEquals("Example League", league.name)
        assertEquals("example-league", league.slug)
        assertEquals("https://example.com/leagues/example-league", league.url)
    }
}

