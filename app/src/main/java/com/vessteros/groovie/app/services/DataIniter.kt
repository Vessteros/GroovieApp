package com.vessteros.groovie.app.services

import com.vessteros.groovie.app.services.cloud.CloudIniter
import com.vessteros.groovie.app.services.db.DBIniter

object DataIniter {
    fun init() {
        CloudIniter.init()
        DBIniter.init()
    }
}