package com.example.core.domain.repository

import com.example.core.domain.model.NewsTech
import kotlinx.coroutines.flow.Flow

interface INewsTechRepository {

    fun getAllNewsTech(): Flow<com.example.core.source.Resource<List<NewsTech>>>

    fun getFavoriteNewsTech(): Flow<List<NewsTech>>

    fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean)
}