package com.vessteros.groovie.app.services.cloud

import com.vessteros.groovie.app.services.cloud.clients.GClient
import com.vessteros.groovie.app.services.cloud.headers.GHeaders

object CloudIniter {
    fun init() {
        initClients()
        initHeaders()
    }

    private fun initClients() {
        GClient.init()
    }

    private fun initHeaders() {
        GHeaders.init()
    }
}