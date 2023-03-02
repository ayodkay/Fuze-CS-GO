package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class GameWinnerTest {

    @Test
    fun testGameWinner() {
        val gameWinner = GameWinner(1, "player")
        assertEquals(1, gameWinner.id)
        assertEquals("player", gameWinner.type)
    }
}

