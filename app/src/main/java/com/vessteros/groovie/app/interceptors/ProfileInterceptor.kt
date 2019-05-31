package com.vessteros.groovie.app.interceptors

import com.vessteros.groovie.app.helpers.mappers.LoginMapper
import com.vessteros.groovie.app.models.GUser
import com.vessteros.groovie.app.models.cloud.requests.Requests.*
import com.vessteros.groovie.app.presenters.UserProfilePresenter
import com.vessteros.groovie.app.services.cloud.clients.GClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileInterceptor(private val presenter: UserProfilePresenter) {
    /**
     * Проверка валидности пароля
     */
    fun checkUserPassword(request: AuthRequest) = GClient.client
        .checkPassword(request)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({}, { presenter.setIssue() })

    /**
     * Сохранение изменений профайла
     */
    fun saveProfileChanges(request: ProfileChangesRequest) = GClient.client
        .profileUpdate(request)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            presenter.onProfileSaved()
        }, {
            presenter.onProfileSaveFail()
        })

    fun getUser() = GClient.client
        .getUser(GUser.id!!)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(LoginMapper::map)
        .subscribe({
            presenter.onUserFound(it)
        }, {
            presenter.back()
        })
}