package com.example.fuze.data.repo

import com.example.fuze.data.PandaScoreService
import com.example.fuze.data.Status
import com.example.fuze.data.subscribeWithRelay
import com.example.fuze.models.MatchResponse
import com.jakewharton.rxrelay3.BehaviorRelay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MatchesRepository {
    fun getMatches(): BehaviorRelay<Status<List<MatchResponse>>>
}

class MatchesRepositoryImpl : MatchesRepository, KoinComponent {
    private val pandaScoreService: PandaScoreService by inject()

    override fun getMatches(): BehaviorRelay<Status<List<MatchResponse>>> =
        BehaviorRelay.create<Status<List<MatchResponse>>?>().also { relay ->
            relay.accept(Status.Loading())
            pandaScoreService.getMatches().subscribeWithRelay(relay) { matchesResponse ->
                matchesResponse
            }
        }

}