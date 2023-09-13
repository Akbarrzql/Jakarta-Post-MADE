package com.example.jakartapostdicoding.core.domain.usecase

import com.example.jakartapostdicoding.core.domain.model.NewsTech
import com.example.jakartapostdicoding.core.domain.repository.INewsTechRepository

class NewsTechInteracator(private val newsTechRepository: INewsTechRepository): NewsTechUseCase {

    override fun getAllNewsTech() = newsTechRepository.getAllNewsTech()

    override fun getFavoriteNewsTech() = newsTechRepository.getFavoriteNewsTech()

    override fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean) =
        newsTechRepository.setFavoriteNewsTech(newsTech, state)

}