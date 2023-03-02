package com.example.fuze.models


import org.junit.Assert.assertEquals
import org.junit.Test

class GameTest {

    @Test
    fun testGame() {
        val winner = GameWinner(1, "player")
        val game = Game(
            begin_at = "2022-01-01T12:00:00Z",
            complete = true,
            detailed_stats = true,
            end_at = "2022-01-01T12:30:00Z",
            finished = true,
            forfeit = false,
            id = 123,
            length = 30,
            match_id = 456,
            position = 1,
            status = "finished",
            winner = winner,
            winner_type = "player"
        )
        assertEquals("2022-01-01T12:00:00Z", game.begin_at)
        assertEquals(true, game.complete)
        assertEquals(true, game.detailed_stats)
        assertEquals("2022-01-01T12:30:00Z", game.end_at)
        assertEquals(true, game.finished)
        assertEquals(false, game.forfeit)
        assertEquals(123, game.id)
        assertEquals(30, game.length)
        assertEquals(456, game.match_id)
        assertEquals(1, game.position)
        assertEquals("finished", game.status)
        assertEquals(winner, game.winner)
        assertEquals("player", game.winner_type)
    }
}

