package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class VideogameTest {

    @Test
    fun testConstructor() {
        val videoGame = VideoGame(1, "Super Mario Bros.", "super-mario-bros")
        assertEquals(1, videoGame.id)
        assertEquals("Super Mario Bros.", videoGame.name)
        assertEquals("super-mario-bros", videoGame.slug)
    }
}
