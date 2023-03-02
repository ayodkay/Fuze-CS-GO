package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class LiveTest {

    @Test
    fun testLive() {
        val live = Live(
            opens_at = null,
            supported = true,
            url = "https://example.com/live"
        )
        assertEquals(null, live.opens_at)
        assertEquals(true, live.supported)
        assertEquals("https://example.com/live", live.url)
    }
}
