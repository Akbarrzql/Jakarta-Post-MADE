package com.example.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_tech")
data class NewsTechEntity (

    @ColumnInfo("image")
    var image: String,

    @ColumnInfo("premium_badge")
    var premiumBadge: String,

    @ColumnInfo("pusblised_at")
    var pusblisedAt: String,

    @ColumnInfo("link")
    var link: String,

    @PrimaryKey
    @ColumnInfo("title")
    var title: String,

    @ColumnInfo("category")
    var category: String,

    @ColumnInfo("headline")
    var headline: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)