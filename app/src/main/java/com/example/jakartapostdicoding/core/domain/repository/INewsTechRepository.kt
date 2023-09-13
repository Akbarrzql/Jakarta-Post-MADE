package com.example.jakartapostdicoding.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.jakartapostdicoding.core.data.source.Resource
import com.example.jakartapostdicoding.core.domain.model.NewsTech

interface INewsTechRepository {

    fun getAllNewsTech(): LiveData<Resource<List<NewsTech>>>

    fun getFavoriteNewsTech(): LiveData<List<NewsTech>>

    fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean)
}