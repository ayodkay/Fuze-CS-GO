package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerTest {
    @Test
    fun testPlayer() {
        val player = Player(
            "Lionel",
            10,
            "https://example.com/image.jpg",
            "Messi",
            "2022-01-01T00:00:00Z",
            "Lionel Messi",
            "Argentina",
            "Forward",
            "lionel-messi"
        )
        assertEquals("Lionel", player.first_name)
        assertEquals(10, player.id)
        assertEquals("https://example.com/image.jpg", player.image_url)
        assertEquals("Messi", player.last_name)
        assertEquals("2022-01-01T00:00:00Z", player.modified_at)
        assertEquals("Lionel Messi", player.name)
        assertEquals("Argentina", player.nationality)
        assertEquals("Forward", player.role)
        assertEquals("lionel-messi", player.slug)
    }
}
