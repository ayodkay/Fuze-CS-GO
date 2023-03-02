package com.example.fuze

import com.example.fuze.data.Error
import com.example.fuze.data.PandaScoreService
import com.example.fuze.data.Status
import com.example.fuze.data.repo.PlayerRepositoryImpl
import com.example.fuze.data.toThrowable
import com.example.fuze.models.MatchResponse
import com.example.fuze.models.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Response

class PlayerRepositoryImplTest : KoinTest {

    private lateinit var pandaScoreService: PandaScoreService
    private lateinit var playersRepositoryImpl: PlayerRepositoryImpl

    @Before
    fun setUp() {
        pandaScoreService = mockk()
        startKoin {
            modules(module {
                single { pandaScoreService }
            })
        }
        playersRepositoryImpl = PlayerRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getPlayers returns BehaviorRelay with Loading status and then Success status`() {
        val players = listOf(
            Player(
                first_name = "",
                id = 0,
                image_url = "",
                last_name = "",
                modified_at = "",
                name = "",
                nationality = "",
                role = "",
                slug = ""
            )
        )

        every { pandaScoreService.getPlayers(1234L) } returns Flowable.just(
            Response.success(
                players
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<Player>>>()
        val loading = Status.Loading<List<Player>>()
        statuses.add(loading)
        val dispose = playersRepositoryImpl.getPlayers(1234L).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        assertEquals(2, statuses.size)
        assertEquals(loading, statuses[0])
        assertEquals(Status.OnSuccess(players), statuses[1])

        // Verify that the PandaScoreService was called with the correct arguments
        verify { pandaScoreService.getPlayers(1234L) }
        dispose.dispose()
    }

    @Test
    fun `getPlayers returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the PandaScoreService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { pandaScoreService.getPlayers(1234L) } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<Player>>>()
        val loading = Status.Loading<List<Player>>()
        statuses.add(loading)
        val dispose = playersRepositoryImpl.getPlayers(1234L).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        assertEquals(2, statuses.size)
        assertEquals(loading, statuses[0])
        assertEquals(
            Status.OnError<MatchResponse>(Error(error)).error.message,
            (statuses[1] as Status.OnError).error.message
        )

        // Verify that the PandaScoreService was called with the correct arguments
        verify { pandaScoreService.getPlayers(1234L) }

        dispose.dispose()
    }
}
