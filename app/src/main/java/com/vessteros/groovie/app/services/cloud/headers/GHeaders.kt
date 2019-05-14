package com.vessteros.groovie.app.services.cloud.headers

import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*

object GHeaders: BaseHeaders {
    override val client: OkHttpClient
        get() = Builder().addInterceptor {
            val request = it
                .request()
                .newBuilder()
                .header("meh", "nyan")

            it.proceed(request.build())
        }
            .build()

    override fun init(): OkHttpClient = client
}