package com.example.fuze.ui.match

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fuze.data.loading
import com.example.fuze.data.onError
import com.example.fuze.data.onSuccess
import com.example.fuze.data.repo.MatchesRepository
import com.example.fuze.models.MatchResponse
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MatchViewModel : ViewModel(), KoinComponent, MatchListeners {
    private val matchesRepository: MatchesRepository by inject()

    val listeners = this

    val event = MutableLiveData(false)

    val swipeEvent = MutableLiveData(false)

    val matchResponse = ObservableField<MatchResponse>()
    val matchResponseList = ObservableField<List<MatchResponse>>()
    val loading = ObservableField<Boolean>()
    val error = ObservableField<String>()

    var dispose: Disposable? = null

    init {
        dispose = getMatches()
    }

    fun getMatches() = matchesRepository.getMatches().subscribe { status ->
        status.onSuccess { matchResponses ->
            var sort = matchResponses.sortedBy { it.begin_at }
            val hasRunning = matchResponses.any { it.status == "running" }
            if (hasRunning) sort = matchResponses.sortedByDescending { it.status == "running" }
            matchResponseList.set(sort)
            loading.set(false)
            swipeEvent.postValue(true)
        }.loading {
            loading.set(true)
        }.onError {
            error.set(it.message)
            loading.set(false)
            swipeEvent.postValue(true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dispose?.dispose()
    }

    override fun onClick(match: MatchResponse) {
        matchResponse.set(match)
        event.postValue(true)
    }
}


interface MatchListeners {
    fun onClick(match: MatchResponse)
}