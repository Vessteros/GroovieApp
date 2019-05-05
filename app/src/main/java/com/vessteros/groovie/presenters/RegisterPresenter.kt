package com.vessteros.groovie.presenters

import android.content.Intent
import android.widget.Toast
import com.vessteros.groovie.activities.LoginActivity
import com.vessteros.groovie.activities.RegisterActivity
import com.vessteros.groovie.apiDataSources.ApiRequestMethods
import com.vessteros.groovie.apiDataSources.Requests.*
import com.vessteros.groovie.apiDataSources.Responses.*
import com.vessteros.groovie.entities.GUser
import com.vessteros.groovie.entities.issues.ApiIssue
import com.vessteros.groovie.entities.issues.InputIssue
import com.vessteros.groovie.helpers.*
import com.vessteros.groovie.helpers.utils.necessityReached
import com.vessteros.groovie.helpers.utils.onDataExists
import com.vessteros.groovie.helpers.utils.onProblemExists
import com.vessteros.groovie.helpers.utils.succeed
import com.vessteros.groovie.models.db.DBWorker
import com.vessteros.groovie.models.db.entities.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*

class RegisterPresenter(private val view: RegisterActivity) {

    /**
     * Объект базы данных
     */
    val dbWorker: DBWorker = DBWorker()

    fun registerAction() {
        val issue = checkFieldsNotEmpty()

        if (issue == null) sendAndParseRequest() else view.issue(issue)
    }

    /**
     * Отправка запроса и обработка ответа
     */
    private fun sendAndParseRequest() {
        GUser.apply {
            login = view.login.text.toString()
            password = view.login.text.toString()
        }

        val request = Request(
            BaseServiceData(),
            RegisterData(
                view.login.text.toString(),
                view.password.text.toString(),
                view.rePass.text.toString()
            )
        )

        RetrofitClient
            .setBaseUrl()
            .create(ApiRequestMethods::class.java).apply {
                register(request)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        parseResponse(it)
                    }, {
                        Toast.makeText(view, it.toString(), Toast.LENGTH_LONG).show()
                    })
            }
    }

    private fun <T : Response<AuthResponse, BaseProblem>> parseResponse(response: T): Unit = when {
            response.succeed() -> {
                response.onDataExists {
                    data!!.necessityReached {
                        GUser.apply {
                            id = it.id
                            token = it.token
                        }

                        saveUser()

                        view.moveOn(Intent(view, LoginActivity::class.java))
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
     * todo: Вынести в репозиторий
     */
    private fun saveUser() {
        dbWorker.realm.executeTransaction { realm ->
            val user = realm.createObject(User::class.java)

            user.id = GUser.id!! // метод вызывается в лябде, проверенной на непустоту id и token
            user.token = GUser.token!!
            user.login = GUser.login!! // логин и пароль были проверены на непустоту в начале метода регистрации
            user.password = GUser.password!!
        }
    }

    /**
     * Проверка заполненных полей на непустоту
     *
     * todo: разнести
     */
    private fun checkFieldsNotEmpty(): InputIssue? = when {
        view.login.text.isEmpty() -> {
            val issue = InputIssue.EmptyLoginField.apply {
                executable = {
                    Toast.makeText(view, message, Toast.LENGTH_LONG).show()

                    view.login.setTextColor(textColor)
                    view.login.setBackgroundColor(inputColor)
                }
            }

            issue
        }

        view.password.text.isEmpty() || view.rePass.text.isEmpty() -> {
            val issue = InputIssue.PasswordFieldsEmpty.apply {
                executable = {
                    Toast.makeText(view, message, Toast.LENGTH_LONG).show()

                    view.password.setTextColor(textColor)
                    view.password.setBackgroundColor(inputColor)

                    view.rePass.setTextColor(textColor)
                    view.rePass.setBackgroundColor(inputColor)
                }
            }

            issue
        }

        view.password.text.toString() != view.rePass.text.toString() -> {
            val issue = InputIssue.PasswordFieldsNotEqual.apply {
                executable = {
                    Toast.makeText(view, message, Toast.LENGTH_LONG).show()

                    view.password.setTextColor(textColor)
                    view.password.setBackgroundColor(inputColor)

                    view.rePass.setTextColor(textColor)
                    view.rePass.setBackgroundColor(inputColor)
                }
            }

            issue
        }

        else -> null
    }
}