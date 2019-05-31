package com.vessteros.groovie.app.models

import com.vessteros.groovie.app.models.db.User
import com.vessteros.groovie.app.services.db.repositories.UserRepository

object GUser {
    val id: Int?
        get() = user?.id

    val token: String?
        get() = user?.token

    val user: User?
        get() = UserRepository().getActiveUser()
}