package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class OpponentsTest {

    @Test
    fun testOpponents() {
        val opponent = Opponent(
            acronym = "OPP",
            id = 1,
            image_url = "https://example.com/image.png",
            location = "Example Location",
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example Opponent",
            slug = "example-opponent"
        )
        val opponents = Opponents(opponent, "team")
        assertEquals(opponent, opponents.opponent)
        assertEquals("team", opponents.type)
    }
}
