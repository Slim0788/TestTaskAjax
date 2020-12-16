package com.testtask.contacts.dependency

import androidx.room.Room
import com.testtask.contacts.App
import com.testtask.contacts.api.AjaxServiceApi
import com.testtask.contacts.db.AppDatabase
import com.testtask.contacts.network.NetworkSource
import com.testtask.contacts.network.NetworkSourceImpl
import com.testtask.contacts.repository.EventsMapper
import com.testtask.contacts.repository.EventsRepository
import com.testtask.contacts.repository.EventsRepositoryImpl
import com.testtask.contacts.repository.cache.EventsCache
import com.testtask.contacts.repository.cache.EventsCacheImpl
import com.testtask.contacts.repository.cache.SharedPreferencesImpl
import com.testtask.contacts.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://randomuser.me/"
private const val NAME_DB = "persons.db"

object Dependencies {

    val db by lazy {
        createRoomDatabase()
    }

    val eventsRepository by lazy {
        createMoviesRepository()
    }

    val eventsMapper by lazy {
        createEventsMapper()
    }

    private fun createMoviesRepository(): EventsRepository {
        return EventsRepositoryImpl(
            createAjaxServiceApi(),
            createNetworkSource(),
            createEventsCache()
        )
    }

    private fun createAjaxServiceApi(): AjaxServiceApi {
        return createRetrofit().create()
    }

    private fun createNetworkSource(): NetworkSource {
        return NetworkSourceImpl()
    }

    private fun createEventsMapper(): EventsMapper {
        return EventsMapper()
    }

    private fun createEventsCache(): EventsCache {
        return EventsCacheImpl(
            db.responseDao(),
            SharedPreferencesImpl(App.instance)
        )
    }

    private fun getOkHttpClient() =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(createLoggingInterceptor())
            .build()

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return HttpLoggingInterceptor()
    }

    private fun createRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getOkHttpClient())
            .build()

    private fun createRoomDatabase() =
        Room.databaseBuilder(
            App.instance,
            AppDatabase::class.java,
            NAME_DB
        ).allowMainThreadQueries()
            .build()

}