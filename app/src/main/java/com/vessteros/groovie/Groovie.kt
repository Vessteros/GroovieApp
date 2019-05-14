package com.vessteros.groovie

import android.app.Application
import com.vessteros.groovie.app.services.DataIniter
import io.realm.Realm
import io.realm.RealmConfiguration

class Groovie: Application() {
    override fun onCreate() {
        super.onCreate()

        initRealm()
        dataInit()
    }

    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun dataInit(): Unit = DataIniter.init()
}