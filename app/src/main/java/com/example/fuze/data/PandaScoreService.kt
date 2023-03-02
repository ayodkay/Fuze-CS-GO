package com.example.fuze.data


import com.example.fuze.models.MatchResponse
import com.example.fuze.models.Player
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PandaScoreService {
    @GET("matches?sort=&page=1&per_page=50")
    fun getMatches(): Flowable<Response<List<MatchResponse>>>

    @GET("matches")
    fun getMatches(
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): Flowable<Response<List<MatchResponse>>>

    @GET("players?sort=&page=1&per_page=50")
    fun getPlayers(@Query("filter[team_id]") id: Long): Flowable<Response<List<Player>>>
}