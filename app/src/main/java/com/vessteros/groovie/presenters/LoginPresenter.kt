package com.vessteros.groovie.presenters

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import com.vessteros.groovie.activities.LoginActivity
import com.vessteros.groovie.activities.MainActivity
import com.vessteros.groovie.apiDataSources.ApiRequestMethods
import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.apiDataSources.Responses
import com.vessteros.groovie.apiDataSources.Responses.*
import com.vessteros.groovie.entities.GUser
import com.vessteros.groovie.entities.issues.ApiIssue
import com.vessteros.groovie.entities.issues.InputIssue
import com.vessteros.groovie.apiDataSources.Responses.Response as Resp
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

class LoginPresenter(private val view: LoginActivity) {

    val dbWorker = DBWorker()

    @SuppressLint("SetTextI18n")
    fun loginAction() {
        val issue = checkFieldsNotEmpty()

        if (issue == null) sendAndParseRequest() else view.issue(issue)
    }

    private fun sendAndParseRequest() {
        GUser.apply {
            login = view.login.text.toString()
            password = view.password.text.toString()
        }

        val request = Request(
            BaseServiceData(),
            AuthData(
                view.login.text.toString(),
                view.password.text.toString()
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
                    }, {
                        Toast.makeText(view, it.toString(), Toast.LENGTH_LONG).show()
                    })
            }
    }

    private fun <T : Responses.Response<AuthResponse, BaseProblem>> parseResponse(response: T) = when {
        response.succeed() -> {
            response.onDataExists {
                data!!.necessityReached {
                    GUser.apply {
                        id = it.id
                        token = it.token
                    }

                    setUserActive()

                    view.moveOn(Intent(view, MainActivity::class.java))
                }
            }

            response.onProblemExists {
                view.issue(ApiIssue.EmptyAuthData.apply {
                    Toast.makeText(view, message, Toast.LENGTH_LONG).show()
                })
            }
        }

        else -> { }
    }

    /**
     * Все пользователи деактивируются
     * Ищется текущий авторизовываемый пользователь:
     * > если есть - проставляем активность
     * > если нет - добавляем нового
     */
    private fun setUserActive() {
        dbWorker.realm.executeTransaction {
            val userList: RealmResults<User> = dbWorker.realm.where(User::class.java)
                .equalTo("isActive", true)
                .findAll()

            userList.forEach { it.isActive = false }

            var user = dbWorker.realm.where(User::class.java)
                .equalTo("id", GUser.id)
                .findFirst()

            if (user != null) {
                user.isActive = true
            } else {
                user = dbWorker.realm.createObject(User::class.java)
                user.id = GUser.id!!
                user.token = GUser.token!!
                user.login = GUser.login!!
                user.password = GUser.password!!
                user.isActive = true
            }
        }
    }

    /**
     * Проверка заполняемых полей на непустоту
     */
    private fun checkFieldsNotEmpty(): InputIssue? = when {
        view.login.text.isEmpty() -> {
            val issue = InputIssue.EmptyLoginField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.login.setTextColor(this.textColor)
                    view.login.setBackgroundColor(this.inputColor)
                }
            }

            issue
        }

        view.password.text.isEmpty() -> {
            val issue = InputIssue.EmptyPasswordField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.password.setTextColor(this.textColor)
                    view.password.setBackgroundColor(this.inputColor)
                }
            }

            issue
        }

        else -> null
    }
}