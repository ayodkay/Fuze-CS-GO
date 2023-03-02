package com.example.fuze.ui.details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fuze.beautify
import com.example.fuze.data.onSuccess
import com.example.fuze.data.repo.PlayersRepository
import com.example.fuze.ifNull
import com.example.fuze.models.MatchResponse
import com.example.fuze.models.Opponent
import com.example.fuze.models.Player
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class MatchDetailsViewModel : ViewModel(), KoinComponent {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val matchResponse = ObservableField<MatchResponse>()
    val event = MutableLiveData(false)

    val header = ObservableField("")
    val dateTime = ObservableField("")
    val opponent1 = ObservableField<Opponent>()
    val opponent2 = ObservableField<Opponent>()

    private val playersRepository: PlayersRepository by inject()

    val opponent1Players = ObservableField<List<Player>>()
    val opponent2Players = ObservableField<List<Player>>()

    fun setValues() {
        matchResponse.get()?.let {
            header.set("${it.league?.name.ifNull { "" }} ${matchResponse.get()?.serie?.name.ifNull { "" }}")
            dateTime.set(
                if (it.begin_at != null) {
                    LocalDateTime.parse(matchResponse.get()?.begin_at, formatter).beautify()
                } else {
                    it.status
                }
            )
            opponent1.set(it.getOpponent1()?.opponent)
            opponent2.set(it.getOpponent2()?.opponent)
        }
    }

    fun goBack() {
        event.postValue(true)
    }

    fun getPlayer() = Observable.combineLatest(
        playersRepository.getPlayers(opponent1.get()?.id ?: 0L),
        playersRepository.getPlayers(opponent2.get()?.id ?: 0L)
    ) { opponent1player, opponent2player -> opponent1player to opponent2player }
        .subscribeOn(Schedulers.io())
        .subscribe({ combinedResponse ->
            combinedResponse.first.onSuccess {
                opponent1Players.set(it)
            }

            combinedResponse.second.onSuccess {
                opponent2Players.set(it)
            }
        }, { _ ->

        })
}