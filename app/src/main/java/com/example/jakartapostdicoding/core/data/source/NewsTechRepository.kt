package com.example.jakartapostdicoding.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.jakartapostdicoding.core.data.source.local.LocalDataSource
import com.example.jakartapostdicoding.core.data.source.remote.RemoteDataSource
import com.example.jakartapostdicoding.core.data.source.remote.network.ApiResponse
import com.example.jakartapostdicoding.core.data.source.remote.response.NewsTechResponse
import com.example.jakartapostdicoding.core.data.source.remote.response.PostsItem
import com.example.jakartapostdicoding.core.domain.model.NewsTech
import com.example.jakartapostdicoding.core.domain.repository.INewsTechRepository
import com.example.jakartapostdicoding.core.utils.AppExecutors
import com.example.jakartapostdicoding.core.utils.DataMapper

class NewsTechRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsTechRepository {

    companion object{
        @Volatile
        private var instance: NewsTechRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): NewsTechRepository =
            instance ?: synchronized(this){
                instance ?: NewsTechRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllNewsTech(): LiveData<Resource<List<NewsTech>>> = object : NetworkBoundResource<List<NewsTech>, List<PostsItem>>(appExecutors){
        override fun loadFromDB(): LiveData<List<NewsTech>> {
            return Transformations.map(localDataSource.getAllNewsTech()){
                DataMapper.mapEntitiesToDomain(it)
            }
        }

        override fun shouldFetch(data: List<NewsTech>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun createCall(): LiveData<ApiResponse<List<PostsItem>>> {
            return remoteDataSource.getAllNewsTech()
        }

        override fun saveCallResult(data: List<PostsItem>) {
            val newsTechList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertNewsTech(newsTechList)
        }
    }.asLiveData()

    override fun getFavoriteNewsTech(): LiveData<List<NewsTech>> {
        return Transformations.map(localDataSource.getFavoriteNewsTech()){
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNewsTech(newsTech: NewsTech, state: Boolean) {
        val newsTechEntity = DataMapper.mapDomainToEntity(newsTech)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNewsTech(newsTechEntity, state) }
    }

}