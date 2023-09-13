package com.example.jakartapostdicoding.di


import com.example.core.domain.usecase.NewsTechInteracator
import com.example.core.domain.usecase.NewsTechUseCase
import com.example.jakartapostdicoding.detail.DetailViewModel
import com.example.jakartapostdicoding.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsTechUseCase> { NewsTechInteracator(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}