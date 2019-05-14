package com.vessteros.groovie.app.services.cloud

import com.vessteros.groovie.app.services.cloud.methods.GMethods

interface Client {
    val baseUrl: String

    val client: GMethods

    fun init(): Any
}