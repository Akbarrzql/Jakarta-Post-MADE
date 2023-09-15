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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("newsTech".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            NewsTechDatabase::class.java, "NewsTech.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "jakpost.vercel.app"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Nh/K70h1NaklfsFAXO88X4w5y0kG3gI2SYw0SOHGbfo=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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