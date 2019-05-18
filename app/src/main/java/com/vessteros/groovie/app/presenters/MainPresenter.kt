package com.vessteros.groovie.app.presenters

import android.content.Intent
import android.widget.Toast
import com.vessteros.groovie.app.activities.LoginActivity
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vessteros.groovie.app.fragments.main.NetworkUIFragment
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.fragments.main.SettingsFragment
import com.vessteros.groovie.app.services.db.repositories.UserRepository

class MainPresenter(val view: MainActivity): BasePresenter {

    val userRepository = UserRepository()

    val profileWorker = ProfileWorker()
    val networkWorker = NetworkWorker()
    val settingsWorker = SettingsWorker()

    inner class ProfileWorker: BasePresenter.BaseWorker<ProfileFragment> {

        override var fragment: ProfileFragment? = null

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
    }

    inner class NetworkWorker: BasePresenter.BaseWorker<NetworkUIFragment> {

        override var fragment: NetworkUIFragment? = null

        override fun attach(fragment: NetworkUIFragment) {
            this.fragment = fragment
        }

        fun startNetworkService(networkId: String) {
            val intent = Intent(view, VkActivity::class.java)
                .putExtra("networkId", networkId)

            view.moveOn(intent)
        }

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