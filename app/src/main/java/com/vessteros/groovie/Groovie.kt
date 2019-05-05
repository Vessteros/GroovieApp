package com.vessteros.groovie

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Groovie: Application() {
    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}