package com.example.fuze.models

import org.junit.Assert.*
import org.junit.Test

class MatchesResponseTest {

    private val opponent1 = Opponent(
        acronym = "ABC",
        id = 123,
        image_url = "https://example.com/opponent1.jpg",
        location = "Location 1",
        modified_at = "2022-02-28T15:00:00Z",
        name = "Opponent 1",
        slug = "opponent-1"
    )
    private val opponent2 = Opponent(
        acronym = "DEF",
        id = 456,
        image_url = "https://example.com/opponent2.jpg",
        location = "Location 2",
        modified_at = "2022-02-28T15:00:00Z",
        name = "Opponent 2",
        slug = "opponent-2"
    )

    private val videogame = VideoGame(1, "Test Game", "test-game")

    private val matchWinner = MatchWinner(
        acronym = null,
        id = 456,
        image_url = "https://example.com/match-winner.jpg",
        location = "Location 2",
        modified_at = "2022-02-28T15:00:00Z",
        name = "Match Winner",
        slug = "match-winner"
    )


    private val league = League(
        id = 1,
        image_url = "https://example.com/image.jpg",
        modified_at = "2022-02-28T15:00:00Z",
        name = "Test League",
        slug = "test-league",
        url = "https://example.com/test-league"
    )

    private val opponents = listOf(Opponents(opponent1, "team"), Opponents(opponent2, "team"))

    private val matchesResponse = MatchResponse(
        begin_at = "2022-02-28T15:00:00Z",
        detailed_stats = true,
        draw = false,
        end_at = "2022-02-28T16:00:00Z",
        forfeit = false,
        game_advantage = null,
        games = emptyList(),
        id = 789,
        league = league,
        league_id = 1,
        live = Live(null, true, "https://example.com/live"),
        match_type = "best_of",
        modified_at = "2022-02-28T15:00:00Z",
        name = "Test Match",
        number_of_games = 3,
        opponents = opponents,
        original_scheduled_at = "2022-02-28T15:00:00Z",
        rescheduled = false,
        results = emptyList(),
        scheduled_at = "2022-02-28T15:00:00Z",
        serie = Serie(
            begin_at = "2022-01-01T12:00:00Z",
            end_at = null,
            full_name = "Example Full Name",
            id = 1,
            league_id = 2,
            modified_at = "2022-01-01T12:00:00Z",
            name = "Example Name",
            season = "2022",
            slug = "example-slug",
            winner_id = null,
            winner_type = null,
            year = 2022
        ),
        serie_id = 1,
        slug = "test-match",
        status = "not_started",
        streams_list = emptyList(),
        tournament = Tournament(
            begin_at = "2022-02-28T15:00:00Z",
            end_at = "2022-02-28T16:00:00Z",
            id = 1,
            league_id = 1,
            live_supported = true,
            modified_at = "2022-02-28T15:00:00Z",
            name = "Test Tournament",
            prizepool = "10000",
            serie_id = 1,
            slug = "test-tournament",
            tier = "premier",
            winner_id = null,
            winner_type = "team"
        ),
        tournament_id = 1,
        videogame = videogame,
        videogame_version = 0,
        winner = matchWinner,
        winner_id = 456,
        winner_type = "team"
    )

    @Test
    fun testMatchesResponse() {
        // Test getter methods
        assertEquals("2022-02-28T15:00:00Z", matchesResponse.begin_at)
        assertTrue(matchesResponse.detailed_stats == true)
        assertFalse(matchesResponse.draw == true)
        assertEquals("2022-02-28T16:00:00Z", matchesResponse.end_at)
        assertFalse(matchesResponse.forfeit == true)
        assertNull(matchesResponse.game_advantage)
        assertTrue(matchesResponse.games?.isEmpty() == true)
        assertEquals(789, matchesResponse.id)
        assertEquals(league, matchesResponse.league)
        assertEquals(1, matchesResponse.league_id)
        assertEquals(Live(null, true, "https://example.com/live"), matchesResponse.live)
        assertEquals("best_of", matchesResponse.match_type)
        assertEquals("2022-02-28T15:00:00Z", matchesResponse.modified_at)
        assertEquals("Test Match", matchesResponse.name)
        assertEquals(3, matchesResponse.number_of_games)
        assertEquals(opponents, matchesResponse.opponents)
        assertEquals("2022-02-28T15:00:00Z", matchesResponse.original_scheduled_at)
        assertEquals(false, matchesResponse.rescheduled)
        assertEquals("2022-02-28T15:00:00Z", matchesResponse.scheduled_at)
        assertEquals(
            Serie(
                begin_at = "2022-01-01T12:00:00Z",
                end_at = null,
                full_name = "Example Full Name",
                id = 1,
                league_id = 2,
                modified_at = "2022-01-01T12:00:00Z",
                name = "Example Name",
                season = "2022",
                slug = "example-slug",
                winner_id = null,
                winner_type = null,
                year = 2022
            ), matchesResponse.serie
        )
        assertEquals(1, matchesResponse.serie_id)
        assertEquals("test-match", matchesResponse.slug)
        assertEquals("not_started", matchesResponse.status)
        assertEquals(listOf<Streams>(), matchesResponse.streams_list)
        assertEquals(
            Tournament(
                begin_at = "2022-02-28T15:00:00Z",
                end_at = "2022-02-28T16:00:00Z",
                id = 1,
                league_id = 1,
                live_supported = true,
                modified_at = "2022-02-28T15:00:00Z",
                name = "Test Tournament",
                prizepool = "10000",
                serie_id = 1,
                slug = "test-tournament",
                tier = "premier",
                winner_id = null,
                winner_type = "team"
            ), matchesResponse.tournament
        )
        assertEquals(1, matchesResponse.tournament_id)
        assertEquals(videogame, matchesResponse.videogame)
        assertEquals(0, matchesResponse.videogame_version)
        assertEquals(matchWinner, matchesResponse.winner)
        assertEquals(456, matchesResponse.winner_id)
        assertEquals("team", matchesResponse.winner_type)
    }


    @Test
    fun testHasOpponent1() {
        assertTrue(matchesResponse.hasOpponent1())
    }

    @Test
    fun testHasOpponent2() {
        assertTrue(matchesResponse.hasOpponent2())
    }

    @Test
    fun testGetOpponent1() {
        assertEquals(matchesResponse.getOpponent1(), opponents[0])
    }

    @Test
    fun testGetOpponent2() {
        assertEquals(matchesResponse.getOpponent2(), opponents[1])
    }
}
