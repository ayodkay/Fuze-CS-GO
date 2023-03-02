package com.example.fuze.di

import com.example.fuze.MainActivityViewModel
import com.example.fuze.data.repo.MatchesRepository
import com.example.fuze.data.repo.MatchesRepositoryImpl
import com.example.fuze.data.repo.PlayerRepositoryImpl
import com.example.fuze.data.repo.PlayersRepository
import com.example.fuze.ui.details.MatchDetailsViewModel
import com.example.fuze.ui.match.MatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<MatchesRepository> { MatchesRepositoryImpl() }
    single<PlayersRepository> { PlayerRepositoryImpl() }
    viewModel { MatchViewModel() }
    viewModel { MatchDetailsViewModel() }
    viewModel { MainActivityViewModel() }
}