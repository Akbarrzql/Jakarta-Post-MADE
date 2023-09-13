package com.example.jakartapostdicoding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.NewsTechUseCase

class HomeViewModel(newsTechUseCase: NewsTechUseCase) : ViewModel() {
    val newsTech = newsTechUseCase.getAllNewsTech().asLiveData()
}