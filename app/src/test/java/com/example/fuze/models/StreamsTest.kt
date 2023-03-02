package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class StreamsTest {

    @Test
    fun testStreams() {
        val streams = Streams(
            embed_url = "https://example.com/embed",
            language = "en",
            main = true,
            official = false,
            raw_url = "https://example.com/raw"
        )
        assertEquals("https://example.com/embed", streams.embed_url)
        assertEquals("en", streams.language)
        assertEquals(true, streams.main)
        assertEquals(false, streams.official)
        assertEquals("https://example.com/raw", streams.raw_url)
    }
}
