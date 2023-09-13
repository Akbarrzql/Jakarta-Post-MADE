package com.example.core.domain.usecase


import com.example.core.domain.model.NewsTech
import kotlinx.coroutines.flow.Flow

interface NewsTechUseCase {

    fun getAllNewsTech(): Flow<com.example.core.source.Resource<List<NewsTech>>>

    fun getFavoriteNewsTech(): Flow<List<NewsTech>>

    fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean)

}