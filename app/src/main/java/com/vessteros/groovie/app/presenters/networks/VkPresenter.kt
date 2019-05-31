package com.vessteros.groovie.app.presenters.networks

import com.vessteros.groovie.app.activities.networks.NetworkPresenter
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope.*

class VkPresenter(val view: VkActivity) : NetworkPresenter {
    override fun findAuth() = false //todo: implement

    override fun auth() {
        val scopes = setOf(
            NOTIFY,
            FRIENDS,
            PHOTOS,
            STORIES,
            STATUS,
            WALL,
            ADS,
            OFFLINE,
            NOTIFICATIONS,
            STATS,
            EMAIL,
            MARKET
        )

        VK.login(view, scopes)
    }
}