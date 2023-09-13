package com.example.jakartapostdicoding.home

import androidx.lifecycle.ViewModel
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechUseCase

class HomeViewModel(newsTechUseCase: NewsTechUseCase) : ViewModel() {
    val newsTech = newsTechUseCase.getAllNewsTech()
}