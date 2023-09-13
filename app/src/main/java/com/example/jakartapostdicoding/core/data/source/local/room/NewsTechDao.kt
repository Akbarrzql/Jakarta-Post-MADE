package com.example.jakartapostdicoding.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jakartapostdicoding.core.data.source.local.entity.NewsTechEntity

@Dao
interface NewsTechDao {

    @Query("SELECT * FROM news_tech")
    fun getAllNewsTech(): LiveData<List<NewsTechEntity>>

    @Query("SELECT * FROM news_tech where isFavorite = 1")
    fun getFavoriteNewsTech(): LiveData<List<NewsTechEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsTech(newsTech: List<NewsTechEntity>)

    @Update
    fun updateFavoriteNewsTech(newsTech: NewsTechEntity)
}