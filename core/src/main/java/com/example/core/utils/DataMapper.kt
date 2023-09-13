package com.example.core.utils

import com.example.core.domain.model.NewsTech
import com.example.core.source.local.entity.NewsTechEntity
import com.example.core.source.remote.response.PostsItem


object DataMapper {
    fun mapResponsesToEntities(input: List<PostsItem>): List<NewsTechEntity> {
        val newsTechList = ArrayList<NewsTechEntity>()
        input.map {
            val newsTech = NewsTechEntity(
                title = it.title,
                category = it.category,
                headline = it.headline,
                image = it.image,
                premiumBadge = it.premiumBadge,
                pusblisedAt = it.pusblisedAt,
                link = it.link,
                isFavorite = false
            )
            newsTechList.add(newsTech)
        }
        return newsTechList
    }

    fun mapEntitiesToDomain(input: List<NewsTechEntity>): List<NewsTech> =
        input.map {
            NewsTech(
                title = it.title,
                category = it.category,
                headline = it.headline,
                image = it.image,
                premiumBadge = it.premiumBadge,
                pusblisedAt = it.pusblisedAt,
                link = it.link,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: NewsTech) = NewsTechEntity(
        title = input.title,
        category = input.category,
        headline = input.headline,
        image = input.image,
        premiumBadge = input.premiumBadge,
        pusblisedAt = input.pusblisedAt,
        link = input.link,
        isFavorite = input.isFavorite
    )
}