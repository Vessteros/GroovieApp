package com.vessteros.groovie.app.interceptors.main

import com.vessteros.groovie.app.helpers.mappers.LoginMapper
import com.vessteros.groovie.app.models.GUser
import com.vessteros.groovie.app.presenters.MainPresenter
import com.vessteros.groovie.app.services.cloud.clients.GClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileWorkerInterceptor(private val worker: MainPresenter.ProfileWorker) {
    fun getUser() = GClient.client
        .getUser(GUser.id!!)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(LoginMapper::map)
        .subscribe({
            worker.onUserFound(it)
        }, {
            worker.display(it.message)
//            worker.logout()
        })
}