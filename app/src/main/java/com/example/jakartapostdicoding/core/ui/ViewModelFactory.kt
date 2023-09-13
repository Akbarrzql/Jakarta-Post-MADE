package com.example.jakartapostdicoding.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jakartapostdicoding.core.di.Injection
import com.example.jakartapostdicoding.core.domain.usecase.NewsTechUseCase
import com.example.jakartapostdicoding.detail.DetailViewModel
import com.example.jakartapostdicoding.favorite.FavoriteViewModel
import com.example.jakartapostdicoding.home.HomeViewModel

class ViewModelFactory private constructor(private val newsTechUseCase: NewsTechUseCase):
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                     Injection.provideNewsTechUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
             modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                 HomeViewModel(newsTechUseCase) as T
             }
             modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                 FavoriteViewModel(newsTechUseCase) as T
             }
             modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                 DetailViewModel(newsTechUseCase) as T
             }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}