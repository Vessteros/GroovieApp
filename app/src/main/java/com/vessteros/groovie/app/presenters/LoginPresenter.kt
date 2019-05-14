package com.vessteros.groovie.app.presenters

import android.widget.Toast
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.LoginActivity
import com.vessteros.groovie.app.helpers.issues.InputIssue
import com.vessteros.groovie.app.helpers.utils.isEmpty
import com.vessteros.groovie.app.interceptors.LoginInterceptor
import com.vessteros.groovie.app.models.cases.LoginCase
import com.vessteros.groovie.app.models.cases.RegisterCase
import com.vessteros.groovie.app.models.cloud.requests.Requests.*
import com.vessteros.groovie.app.models.entities.User
import com.vessteros.groovie.app.services.db.repositories.UserRepository

class LoginPresenter(private val view: LoginActivity) {
    private val repository = UserRepository()

    private val interceptor = LoginInterceptor(this)

    private var issue: InputIssue? = null

    /************************************* Методы UI *************************************/

    fun loginAction(dataCase: LoginCase) {
        issue = null

        searchForIssues(dataCase)

        if (issue.isEmpty()) performAndSendRequest(dataCase) else view.issue(issue!!)
    }

    fun registerAction(dataCase: RegisterCase) {
        issue = null

        searchForIssues(dataCase)

        if (issue.isEmpty()) performAndSendRequest(dataCase) else view.issue(issue!!)
    }

    /************************************* Методы UI *************************************/

    /********************************* Подготовка запроса *********************************/

    private fun performAndSendRequest(dataCase: LoginCase) = interceptor.getUser(
        AuthRequest(dataCase.login!!, dataCase.password!!)
    )

    private fun performAndSendRequest(dataCase: RegisterCase) = interceptor.registerUser(
        RegisterRequest(dataCase.login!!, dataCase.password!!, dataCase.repassword!!)
    )

    /********************************* Подготовка запроса *********************************/

    /******************************** Колбек с интерсептора *******************************/

    fun loginFail(it: Throwable) = Toast.makeText(view, it.toString(), Toast.LENGTH_LONG).show()

    fun registerFail() = Toast.makeText(view, R.string.registerFail, Toast.LENGTH_LONG).show()

    fun logIn(user: User) = user.id?.let {
        setUser(user)

        view.moveOn()
    }

    private fun setUser(user: User) = repository.deactivateUsers().also { enable(user) }

    private fun enable(user: User) {
        val cashedUser = repository.getUserOrNull(user.id!!)

        when {
            cashedUser != null -> repository.enableUser(cashedUser)
            else -> repository.saveUser(user)
        }
    }

    /******************************** Колбек с интерсептора *******************************/

    /**************************** Проверка на ошибки заполнения ***************************/

    private fun searchForIssues(dataCase: LoginCase): InputIssue? = when {
        dataCase.login.isNullOrEmpty() -> {
            issue = InputIssue.EmptyLoginField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.loginFragment.login.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }

            issue
        }

        dataCase.password.isNullOrEmpty() -> {
            issue = InputIssue.EmptyPasswordField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.loginFragment.pass.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }

            issue
        }

        else -> null
    }

    private fun searchForIssues(dataCase: RegisterCase): InputIssue? = when {
        dataCase.login.isNullOrEmpty() -> {
            issue = InputIssue.EmptyLoginField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.registerFragment.login.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }

            issue
        }

        dataCase.password.isNullOrEmpty() -> {
            issue = InputIssue.EmptyPasswordField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.registerFragment.pass.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }

            issue
        }

        dataCase.repassword.isNullOrEmpty() -> {
            issue = InputIssue.PasswordFieldsEmpty.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.registerFragment.repass.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }

            issue
        }

        else -> null
    }

    /**************************** Проверка на ошибки заполнения ***************************/
}