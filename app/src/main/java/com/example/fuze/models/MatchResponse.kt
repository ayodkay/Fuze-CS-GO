package com.example.fuze.models

import java.io.Serializable


data class MatchResponse(
    val begin_at: String?,
    val detailed_stats: Boolean?,
    val draw: Boolean?,
    val end_at: String?,
    val forfeit: Boolean?,
    val game_advantage: Int?,
    val games: List<Game>?,
    val id: Int?,
    val league: League?,
    val league_id: Int?,
    val live: Live?,
    val match_type: String?,
    val modified_at: String?,
    val name: String?,
    val number_of_games: Int?,
    val opponents: List<Opponents>?,
    val original_scheduled_at: String?,
    val rescheduled: Boolean?,
    val results: List<Result>?,
    val scheduled_at: String?,
    val serie: Serie?,
    val serie_id: Int?,
    val slug: String?,
    val status: String?,
    val streams_list: List<Streams>?,
    val tournament: Tournament?,
    val tournament_id: Int?,
    val videogame: VideoGame?,
    val videogame_version: Any?,
    val winner: MatchWinner?,
    val winner_id: Int?,
    val winner_type: String?,
) : Serializable {
    fun hasOpponent1() = (opponents?.size ?: 0) >= 1

    fun hasOpponent2() = (opponents?.size ?: 0) >= 2

    fun getOpponent1() = opponents?.getOrNull(0)

    fun getOpponent2() = opponents?.getOrNull(1)
}