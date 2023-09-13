package com.example.jakartapostdicoding.detail

import androidx.lifecycle.ViewModel
import com.example.jakartapostdicoding.core.domain.model.NewsTech
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechUseCase

class DetailViewModel(private val newsTechUseCase: NewsTechUseCase): ViewModel() {

    fun setFavoriteNewsTech(newsTechTittle: NewsTech, newStatus:Boolean) =
        newsTechUseCase.setFavoriteNewsTech(newsTechTittle, newStatus)
}