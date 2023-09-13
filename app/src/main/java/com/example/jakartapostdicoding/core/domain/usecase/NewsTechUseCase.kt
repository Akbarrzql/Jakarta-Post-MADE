package com.example.jakartapostdicoding.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.jakartapostdicoding.core.data.source.Resource
import com.example.jakartapostdicoding.core.domain.model.NewsTech

interface NewsTechUseCase {

    fun getAllNewsTech(): LiveData<Resource<List<NewsTech>>>

    fun getFavoriteNewsTech(): LiveData<List<NewsTech>>

    fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean)

}