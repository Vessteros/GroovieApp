package com.vessteros.groovie.app.interceptors

import com.vessteros.groovie.app.services.cloud.clients.GClient
import com.vessteros.groovie.app.helpers.mappers.LoginMapper
import com.vessteros.groovie.app.models.cloud.requests.Requests.*
import com.vessteros.groovie.app.presenters.LoginPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInterceptor(private val presenter: LoginPresenter) {

    /**
     * Получение пользователя с api Groovie
     */
    fun getUser(request: AuthRequest) = GClient.client
        .authorize(request)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(LoginMapper::map)
        .subscribe({
            presenter.logIn(it)
        }, {
            presenter.loginFail(it)
        })

    /**
     * Регистрация пользователя
     */
    fun registerUser(request: RegisterRequest) = GClient.client
        .register(request)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(LoginMapper::map)
        .subscribe({
            presenter.logIn(it)
        }, {
            presenter.registerFail()
        })
}