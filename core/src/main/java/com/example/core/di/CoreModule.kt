package com.example.core.di

import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.domain.repository.INewsTechRepository
import com.example.core.source.NewsTechRepository
import com.example.core.source.local.LocalDataSource
import com.example.core.source.local.room.NewsTechDatabase
import com.example.core.source.remote.RemoteDataSource
import com.example.core.source.remote.network.ApiService
import com.example.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsTechDatabase>().newsTechDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsTechDatabase::class.java, "NewsTech.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsTechRepository> { NewsTechRepository(get(), get(), get()) }
}