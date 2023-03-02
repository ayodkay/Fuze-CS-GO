package com.example.fuze.data.repo

import com.example.fuze.data.PandaScoreService
import com.example.fuze.data.Status
import com.example.fuze.data.subscribeWithRelay
import com.example.fuze.models.Player
import com.jakewharton.rxrelay3.BehaviorRelay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface PlayersRepository {
    fun getPlayers(id: Long): BehaviorRelay<Status<List<Player>>>
}

class PlayerRepositoryImpl : PlayersRepository, KoinComponent {
    private val pandaScoreService: PandaScoreService by inject()

    override fun getPlayers(id: Long): BehaviorRelay<Status<List<Player>>> =
        BehaviorRelay.create<Status<List<Player>>?>().also { relay ->
            relay.accept(Status.Loading())
            pandaScoreService.getPlayers(id).subscribeWithRelay(relay) { matchesResponse ->
                matchesResponse
            }
        }

}