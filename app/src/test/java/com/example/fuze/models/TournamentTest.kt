package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class TournamentTest {

    @Test
    fun testTournament() {
        val tournament = Tournament(
            begin_at = "2022-05-01T09:00:00Z",
            end_at = "2022-05-31T23:59:59Z",
            id = 1234,
            league_id = 5678,
            live_supported = true,
            modified_at = "2022-03-15T12:30:00Z",
            name = "Spring Cup",
            prizepool = "$100,000",
            serie_id = 9012,
            slug = "spring-cup-2022",
            tier = "Major",
            winner_id = null,
            winner_type = "Match"
        )
        assertEquals("2022-05-01T09:00:00Z", tournament.begin_at)
        assertEquals("2022-05-31T23:59:59Z", tournament.end_at)
        assertEquals(1234, tournament.id)
        assertEquals(5678, tournament.league_id)
        assertEquals(true, tournament.live_supported)
        assertEquals("2022-03-15T12:30:00Z", tournament.modified_at)
        assertEquals("Spring Cup", tournament.name)
        assertEquals("$100,000", tournament.prizepool)
        assertEquals(9012, tournament.serie_id)
        assertEquals("spring-cup-2022", tournament.slug)
        assertEquals("Major", tournament.tier)
        assertEquals(null, tournament.winner_id)
        assertEquals("Match", tournament.winner_type)
    }
}
