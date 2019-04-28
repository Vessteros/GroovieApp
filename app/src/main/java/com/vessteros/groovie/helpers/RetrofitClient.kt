package com.vessteros.groovie.helpers

import com.vessteros.groovie.apiDataSources.ApiRequestMethods
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Клиент сервиса обращения к серверу
 */
object RetrofitClient {
    /** Адрес back */
    private const val baseUrl = "http://vessteros.beget.tech/api/"

    /** Клиент для отправки запросов */
    val client = Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequestMethods::class.java)!!
}
