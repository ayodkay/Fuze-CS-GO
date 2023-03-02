package com.example.fuze.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ResultTest {

    @Test
    fun testResult() {
        val result = Result(1, 1L)
        assertEquals(1, result.score)
        assertEquals(1L, result.team_id)
    }
}