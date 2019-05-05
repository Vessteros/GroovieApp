package com.vessteros.groovie.presenters

import android.content.Intent
import android.widget.Toast
import com.vessteros.groovie.activities.LRActivity
import com.vessteros.groovie.activities.MainActivity
import com.vessteros.groovie.activities.StartActivity
import com.vessteros.groovie.apiDataSources.ApiRequestMethods
import com.vessteros.groovie.apiDataSources.Requests
import com.vessteros.groovie.apiDataSources.Responses
import com.vessteros.groovie.entities.GUser
import com.vessteros.groovie.entities.issues.ApiIssue
import com.vessteros.groovie.helpers.RetrofitClient
import com.vessteros.groovie.helpers.utils.necessityReached
import com.vessteros.groovie.helpers.utils.onDataExists
import com.vessteros.groovie.helpers.utils.onProblemExists
import com.vessteros.groovie.helpers.utils.succeed
import com.vessteros.groovie.models.db.DBWorker
import com.vessteros.groovie.models.db.entities.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_login.*

class StartPresenter(private val view: StartActivity) : BasePresenter {
    private val dbWorker = DBWorker()

    fun checkUserActive() {
        val userList: RealmResults<User> = dbWorker.realm.where(User::class.java)
            .equalTo("isActive", true)
            .findAll()

//        dbWorker.realm.executeTransaction {
//            userList.forEach {
//                it.isActive = false
//            }
//        }

        if (userList.count() == 1) {
            userList[0]?.let {
                GUser.apply {
                    id = it.id
                    name = it.name
                    token = it.token
                    login = it.login
                    password = it.password
                    lastName = it.lastName
                    isActive = it.isActive
                }

                checkUserAuth()
            }
        } else {
            userList.forEach { it.isActive = false }

            view.moveOn(Intent(view, LRActivity::class.java))
        }
    }

    private fun checkUserAuth() {
        val request = Requests.Request(
            Requests.BaseServiceData(),
            Requests.AuthData(
                GUser.login!!,
                GUser.password!!
            )
        )

        RetrofitClient
            .setBaseUrl()
            .create(ApiRequestMethods::class.java).apply {
                authorize(request)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        parseResponse(it)

                        view.moveOn(Intent(view, MainActivity::class.java))
                    }, {
                        view.moveOn(Intent(view, LRActivity::class.java))
                    })
            }
    }

    private fun <T : Responses.Response<Responses.AuthResponse, Responses.BaseProblem>> parseResponse(response: T) = when {
        response.succeed() -> {
            response.onDataExists {
                data!!.necessityReached {data ->
                    GUser.apply {
                        id = data.id
                        token = data.token
                    }

                    dbWorker.realm.executeTransaction {
                        val user = dbWorker.realm.where(User::class.java)
                            .equalTo("id", data.id)
                            .findFirst()

                        user!!.apply {
                            token = data.token!!
                            isActive = true
                        }
                    }

                    view.moveOn(Intent(view, MainActivity::class.java))
                }
            }

            response.onProblemExists {
                view.moveOn(Intent(view, LRActivity::class.java))
            }
        }

        else -> { }
    }
}