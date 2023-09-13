package com.example.core.source.local

import com.example.core.source.local.entity.NewsTechEntity
import com.example.core.source.local.room.NewsTechDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsTechDao: NewsTechDao) {

    fun getAllNewsTech(): Flow<List<NewsTechEntity>> = newsTechDao.getAllNewsTech()

    fun getFavoriteNewsTech(): Flow<List<NewsTechEntity>> = newsTechDao.getFavoriteNewsTech()

    suspend fun insertNewsTech(newsTechList: List<NewsTechEntity>) = newsTechDao.insertNewsTech(newsTechList)

    fun setFavoriteNewsTech(newsTech: NewsTechEntity, newState: Boolean) {
        newsTech.isFavorite = newState
        newsTechDao.updateFavoriteNewsTech(newsTech)
    }
}