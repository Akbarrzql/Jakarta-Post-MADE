package com.example.core.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.source.local.entity.NewsTechEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsTechDao {

    @Query("SELECT * FROM news_tech")
    fun getAllNewsTech(): Flow<List<NewsTechEntity>>

    @Query("SELECT * FROM news_tech where isFavorite = 1")
    fun getFavoriteNewsTech(): Flow<List<NewsTechEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsTech(newsTech: List<NewsTechEntity>)

    @Update
    fun updateFavoriteNewsTech(newsTech: NewsTechEntity)
}