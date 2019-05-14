package com.vessteros.groovie.app.services.cloud.headers

import okhttp3.OkHttpClient

interface BaseHeaders {
    val client: OkHttpClient

    fun init(): OkHttpClient
}