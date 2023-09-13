package com.example.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.source.local.entity.NewsTechEntity


@Database(entities = [NewsTechEntity::class], version = 1, exportSchema = false)
abstract class NewsTechDatabase : RoomDatabase() {

    abstract fun newsTechDao(): NewsTechDao

}