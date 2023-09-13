package com.example.jakartapostdicoding.core.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsTech(
    val image: String,
    val premiumBadge: String,
    val pusblisedAt: String,
    val link: String,
    val title: String,
    val category: String,
    val headline: String,
    val isFavorite: Boolean
) : Parcelable