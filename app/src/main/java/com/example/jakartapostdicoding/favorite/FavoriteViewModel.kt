package com.example.jakartapostdicoding.favorite

import androidx.lifecycle.ViewModel
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechUseCase

class FavoriteViewModel(newsTechUseCase: NewsTechUseCase) : ViewModel() {
    val favoriteNewsTech = newsTechUseCase.getFavoriteNewsTech()
}