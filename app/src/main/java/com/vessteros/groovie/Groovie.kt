package com.vessteros.groovie

import android.app.Application
import com.vessteros.groovie.app.services.DataIniter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import io.realm.Realm
import io.realm.RealmConfiguration

class Groovie: Application() {
    override fun onCreate() {
        super.onCreate()

        initRealm()
        dataInit()
        vkInit()
    }

    private fun vkInit() {
        VK.initialize(this)
    }

    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun dataInit(): Unit = DataIniter.init()
}