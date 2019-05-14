package com.vessteros.groovie.app.services.cloud.clients

import com.vessteros.groovie.app.services.cloud.Client
import com.vessteros.groovie.app.services.cloud.headers.GHeaders
import com.vessteros.groovie.app.services.cloud.methods.GMethods
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GClient: Client {
    /** Адрес back */
    override val baseUrl = "http://vessteros.beget.tech/api/"

    /** Клиент для отправки запросов */
    override val client: GMethods
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(GHeaders.client)
            .baseUrl(baseUrl)
            .build()!!
            .create(GMethods::class.java)

    override fun init(): GMethods = client
}