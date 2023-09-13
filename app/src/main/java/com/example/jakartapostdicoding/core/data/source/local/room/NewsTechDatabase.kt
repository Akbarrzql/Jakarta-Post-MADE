package com.example.jakartapostdicoding.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jakartapostdicoding.core.data.source.local.entity.NewsTechEntity


@Database(entities = [NewsTechEntity::class], version = 1, exportSchema = false)
abstract class NewsTechDatabase : RoomDatabase() {

    abstract fun newsTechDao(): NewsTechDao

    companion object{
        @Volatile
        private var INSTANCE: NewsTechDatabase? = null

        fun getInstance(context: Context): NewsTechDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsTechDatabase::class.java,
                    "NewsTech.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}