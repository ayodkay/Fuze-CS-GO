package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class SerieTest {

    @Test
    fun testSerie() {
        val serie = Serie(
            begin_at = "2022-01-01T12:00:00Z",
            end_at = null,
            full_name = "Example Full Name",
            id = 1,
            league_id = 2,
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example Name",
            season = "2022",
            slug = "example-slug",
            winner_id = null,
            winner_type = null,
            year = 2022
        )
        assertEquals("2022-01-01T12:00:00Z", serie.begin_at)
        assertEquals(null, serie.end_at)
        assertEquals("Example Full Name", serie.full_name)
        assertEquals(1, serie.id)
        assertEquals(2, serie.league_id)
        assertEquals("2022-01-01T12:00:00Z", serie.modified_at)
        assertEquals("Example Name", serie.name)
        assertEquals("2022", serie.season)
        assertEquals("example-slug", serie.slug)
        assertEquals(null, serie.winner_id)
        assertEquals(null, serie.winner_type)
        assertEquals(2022, serie.year)
    }
}
