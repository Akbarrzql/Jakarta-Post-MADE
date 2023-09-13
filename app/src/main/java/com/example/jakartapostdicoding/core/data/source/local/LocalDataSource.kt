package com.example.jakartapostdicoding.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.jakartapostdicoding.core.data.source.local.entity.NewsTechEntity
import com.example.jakartapostdicoding.core.data.source.local.room.NewsTechDao

class LocalDataSource private constructor(private val newsTechDao: NewsTechDao) {

    companion object{
        private var instance: LocalDataSource? = null

        fun getInstance(newsTechDao: NewsTechDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(newsTechDao)
            }
    }

    fun getAllNewsTech(): LiveData<List<NewsTechEntity>> = newsTechDao.getAllNewsTech()

    fun getFavoriteNewsTech(): LiveData<List<NewsTechEntity>> = newsTechDao.getFavoriteNewsTech()

    fun insertNewsTech(newsTechList: List<NewsTechEntity>) = newsTechDao.insertNewsTech(newsTechList)

    fun setFavoriteNewsTech(newsTech: NewsTechEntity, newState: Boolean) {
        newsTech.isFavorite = newState
        newsTechDao.updateFavoriteNewsTech(newsTech)
    }
}