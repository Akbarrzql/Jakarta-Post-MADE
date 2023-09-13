package com.example.jakartapostdicoding.core.data.source.remote.network

import com.example.jakartapostdicoding.core.data.source.remote.response.NewsTechResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("category/business/tech")
    fun getNews(): Call<NewsTechResponse>
}