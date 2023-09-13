package com.example.jakartapostdicoding.core.di

import android.content.Context
import com.example.jakartapostdicoding.core.data.source.NewsTechRepository
import com.example.jakartapostdicoding.core.data.source.local.LocalDataSource
import com.example.jakartapostdicoding.core.data.source.local.room.NewsTechDatabase
import com.example.jakartapostdicoding.core.data.source.remote.RemoteDataSource
import com.example.jakartapostdicoding.core.data.source.remote.network.ApiConfig
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechInteracator
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechUseCase
import com.example.jakartapostdicoding.core.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): NewsTechRepository {
        val database = NewsTechDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.newsTechDao())

        val appExecutors = AppExecutors()

        return NewsTechRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideNewsTechUseCase(context: Context): NewsTechUseCase {
        val repository = provideRepository(context)
        return NewsTechInteracator(repository)
    }
}