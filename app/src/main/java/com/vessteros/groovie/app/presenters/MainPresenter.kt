package com.vessteros.groovie.app.presenters

import android.content.Intent
import android.widget.Toast
import com.vessteros.groovie.app.activities.LoginActivity
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vessteros.groovie.app.fragments.main.NetworkUIFragment
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.fragments.main.SettingsFragment
import com.vessteros.groovie.app.interceptors.main.ProfileWorkerInterceptor
import com.vessteros.groovie.app.models.GUser
import com.vessteros.groovie.app.models.entities.User
import com.vessteros.groovie.app.services.db.repositories.UserRepository

class MainPresenter(val view: MainActivity): BasePresenter {

    val userRepository = UserRepository()

    val profileWorker = ProfileWorker()
    val networkWorker = NetworkWorker()
    val settingsWorker = SettingsWorker()

    inner class ProfileWorker: BasePresenter.BaseWorker<ProfileFragment> {

        override var fragment: ProfileFragment? = null

        val interceptor = ProfileWorkerInterceptor(this)

        override fun attach(fragment: ProfileFragment) {
            this.fragment = fragment
        }

        fun logout() {
            userRepository.deactivateUsers()
            view.moveOn(Intent(view, LoginActivity::class.java))
        }

        override fun update() {
            Toast.makeText(view, "Profile updated", Toast.LENGTH_LONG).show()
        }

        fun findUser() = interceptor.getUser()

        fun onUserFound(user: User) = fragment?.setView(user)
        fun display(message: String?) {
            Toast.makeText(view, message, Toast.LENGTH_LONG).show()
        }
    }

    inner class NetworkWorker: BasePresenter.BaseWorker<NetworkUIFragment> {
        private val networkMap = mapOf(
            "vk" to VkActivity::class.java
        )

        override var fragment: NetworkUIFragment? = null

        override fun attach(fragment: NetworkUIFragment) {
            this.fragment = fragment
        }

        fun startNetworkService(networkId: String) = view.moveOn(Intent(view, networkMap[networkId]))

        override fun update() {
            Toast.makeText(view, "NetworkList updated", Toast.LENGTH_LONG).show()
        }
    }

    inner class SettingsWorker: BasePresenter.BaseWorker<SettingsFragment> {
        override var fragment: SettingsFragment? = null

        override fun attach(fragment: SettingsFragment) {
            this.fragment = fragment
        }

        override fun update() {
            Toast.makeText(view, "Settings updated", Toast.LENGTH_LONG).show()
        }
    }
}