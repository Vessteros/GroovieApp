package com.vessteros.groovie.app.presenters.networks

import com.vessteros.groovie.app.activities.networks.NetworkPresenter
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vessteros.groovie.app.interceptors.VkInterceptor


class VkPresenter(val view: VkActivity) : NetworkPresenter {
    val interceptor = VkInterceptor(this)
    override fun findAuth() = false //todo: implement

    override fun auth() {

    }

    fun getContent() {
        interceptor.getPosts()
    }
}