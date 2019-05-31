package com.vessteros.groovie.app.services.cloud.headers

import com.vessteros.groovie.app.models.GUser
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*

object GHeaders: BaseHeaders {
    override val client: OkHttpClient
        get() = Builder().addInterceptor {
            val request = it
                .request()
                .newBuilder()

            GUser.token?.let {
                request.header("token", GUser.token!!)
            }

            it.proceed(request.build())
        }
            .build()

    override fun init(): OkHttpClient = client
}