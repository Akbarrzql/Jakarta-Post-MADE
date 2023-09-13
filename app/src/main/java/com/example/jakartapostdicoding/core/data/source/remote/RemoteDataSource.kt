package com.example.jakartapostdicoding.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jakartapostdicoding.core.data.source.remote.network.ApiResponse
import com.example.jakartapostdicoding.core.data.source.remote.network.ApiService
import com.example.jakartapostdicoding.core.data.source.remote.response.NewsTechResponse
import com.example.jakartapostdicoding.core.data.source.remote.response.PostsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllNewsTech(): LiveData<ApiResponse<List<PostsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<PostsItem>>>()

        //get data from remote api
        val client = apiService.getNews()

        client.enqueue(object : Callback<NewsTechResponse> {
            override fun onResponse(
                call: Call<NewsTechResponse>,
                response: Response<NewsTechResponse>
            ) {
                val dataArray = response.body()?.posts
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<NewsTechResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}