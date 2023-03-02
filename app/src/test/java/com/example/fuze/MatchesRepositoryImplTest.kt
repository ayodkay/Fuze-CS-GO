package com.example.fuze

import com.example.fuze.data.Error
import com.example.fuze.data.PandaScoreService
import com.example.fuze.data.Status
import com.example.fuze.data.repo.MatchesRepositoryImpl
import com.example.fuze.data.toThrowable
import com.example.fuze.models.*
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

class MatchesRepositoryImplTest : KoinTest {
    private lateinit var matchesRepositoryImpl: MatchesRepositoryImpl
    private lateinit var pandaScoreService: PandaScoreService

    @Before
    fun setUp() {
        pandaScoreService = mockk()
        startKoin {
            modules(module {
                single { pandaScoreService }
            })
        }
        matchesRepositoryImpl = MatchesRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getMatches returns BehaviorRelay with Loading status and then Success status`() {
        // Mock a successful response from the PandaScoreService
        val matchesResponse = listOf(
            MatchResponse(
                begin_at = "",
                detailed_stats = false,
                draw = false,
                end_at = "",
                forfeit = false,
                game_advantage = null,
                games = listOf(),
                id = 0,
                league = League(
                    id = 0,
                    image_url = "",
                    modified_at = "",
                    name = "",
                    slug = "",
                    url = ""
                ),
                league_id = 0,
                live = Live(opens_at = null, supported = false, url = ""),
                match_type = "",
                modified_at = "",
                name = "",
                number_of_games = 0,
                opponents = listOf(),
                original_scheduled_at = "",
                rescheduled = false,
                results = listOf(),
                scheduled_at = "",
                serie = Serie(
                    begin_at = "",
                    end_at = null,
                    full_name = "",
                    id = 0,
                    league_id = 0,
                    modified_at = "",
                    name = "",
                    season = "",
                    slug = "",
                    winner_id = null,
                    winner_type = null,
                    year = 0
                ),
                serie_id = 0,
                slug = "",
                status = "",
                streams_list = listOf(),
                tournament = Tournament(
                    begin_at = "",
                    end_at = "",
                    id = 0,
                    league_id = 0,
                    live_supported = false,
                    modified_at = "",
                    name = "",
                    prizepool = "",
                    serie_id = 0,
                    slug = "",
                    tier = "",
                    winner_id = null,
                    winner_type = ""
                ),
                tournament_id = 0,
                videogame = VideoGame(id = 0, name = "", slug = ""),
                videogame_version = .0,
                winner = MatchWinner(
                    acronym = null,
                    id = 0,
                    image_url = "",
                    location = "",
                    modified_at = "",
                    name = "",
                    slug = ""
                ),
                winner_id = 0,
                winner_type = ""
            )
        )
        every { pandaScoreService.getMatches() } returns Flowable.just(
            Response.success(
                matchesResponse
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<MatchResponse>>>()
        val loading = Status.Loading<List<MatchResponse>>()
        statuses.add(loading)
        val dispose = matchesRepositoryImpl.getMatches().subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        assertEquals(2, statuses.size)
        assertEquals(loading, statuses[0])
        assertEquals(Status.OnSuccess(matchesResponse), statuses[1])

        // Verify that the PandaScoreService was called with the correct arguments
        verify { pandaScoreService.getMatches() }
        dispose.dispose()
    }

    @Test
    fun `getMatches returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the PandaScoreService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { pandaScoreService.getMatches() } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<MatchResponse>>>()
        val loading = Status.Loading<List<MatchResponse>>()
        statuses.add(loading)
        val dispose = matchesRepositoryImpl.getMatches().subscribe { status ->
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
        verify { pandaScoreService.getMatches() }

        dispose.dispose()
    }
}
