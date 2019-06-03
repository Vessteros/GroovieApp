package com.vessteros.groovie.app.interceptors.main

import com.google.gson.Gson
import com.vessteros.groovie.app.adapters.ConnectedNetworksListAdapter
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.helpers.mappers.LoginMapper
import com.vessteros.groovie.app.models.GUser
import com.vessteros.groovie.app.models.cloud.responses.data.VkUser
import com.vessteros.groovie.app.presenters.MainPresenter
import com.vessteros.groovie.app.services.cloud.clients.GClient
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.requests.VKRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger

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
            worker.logout()
        })

    fun getConnectedNetworks(fragment: ProfileFragment) = GClient.client
        .getConnectedNetworksList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.data?.network_list?.let {
                fragment.initRecycleView(it)
            }
            Logger.getLogger(ProfileWorkerInterceptor::class.java.name).warning(it.toString())
        }, {
            Logger.getLogger(ProfileWorkerInterceptor::class.java.name).warning(it.toString())
        })

    fun getVkUser(
        adapter: ConnectedNetworksListAdapter,
        p0: ConnectedNetworksListAdapter.ConnectedNetworkViewHolder
    ) {
        val request = VKRequest<Any>("users.get")
        request.addParam("name_case", "nom")
        request.addParam("fields", "photo_400_orig")

        if (VK.isLoggedIn()) VK.execute(request, object : VKApiCallback<Any> {
            override fun fail(error: VKApiExecutionException) {
                Logger.getLogger(ProfileWorkerInterceptor::class.java.name).warning(error.toString())
            }

            override fun success(result: Any) {
                val obj: VkUser.Response = Gson().fromJson(result.toString(), VkUser.Response::class.java)
                adapter.onUserFound(obj.response, p0)
            }
        })
    }
}