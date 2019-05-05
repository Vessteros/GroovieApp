package com.vessteros.groovie.models.db

import io.realm.Realm
import io.realm.RealmObject

class DBWorker {
    val realm: Realm = Realm.getDefaultInstance()

    fun <O : RealmObject> insert(obj: O) {

    }

    fun get() {

    }

    fun exterminate() {
        realm.close()
    }
}