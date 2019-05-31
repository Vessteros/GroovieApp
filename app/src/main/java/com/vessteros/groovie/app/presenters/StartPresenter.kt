package com.vessteros.groovie.app.presenters

import android.content.Intent
import com.vessteros.groovie.app.activities.LoginActivity
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.activities.StartActivity
import com.vessteros.groovie.app.services.db.repositories.UserRepository

class StartPresenter(private val view: StartActivity): BasePresenter {
    private val repository = UserRepository()

    fun checkActiveUser() {
//        repository.deactivateUsers()
        val userList = repository.getActiveUsers()

        when {
            userList.isNullOrEmpty() -> userNotSpecified()
            userList.count() > 1 -> varietyUserActiveness()
            else -> openGate()
        }
    }

    private fun userNotSpecified() = view.moveOn(Intent(view, LoginActivity::class.java))

    private fun varietyUserActiveness() = repository.deactivateUsers().also { userNotSpecified() }

    private fun openGate() = view.moveOn(Intent(view, MainActivity::class.java))
}