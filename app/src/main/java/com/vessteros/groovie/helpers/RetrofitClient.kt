package com.vessteros.groovie.helpers

import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Клиент сервиса обращения к серверу
 */
object RetrofitClient {
    /** Адрес back */
    private const val baseUrl = "http://vessteros.beget.tech/api/"

    /** Клиент для отправки запросов */
    private val client = Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun setBaseUrl(baseUrl: String = this.baseUrl): RetrofitClient {
        client.baseUrl(baseUrl)
        return this
    }

    fun <T> create(jClass: Class<T>): T {
        return client
            .build()
            .create(jClass)
    }
}
