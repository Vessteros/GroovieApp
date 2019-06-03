package com.vessteros.groovie.app.interceptors

import android.widget.Toast
import com.google.gson.Gson
import com.vessteros.groovie.app.models.cloud.responses.data.VkNewsFeedList
import com.vessteros.groovie.app.presenters.networks.VkPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.requests.VKRequest

class VkInterceptor(val presenter: VkPresenter) {
    val newsfeed = "newsfeed.get"

    fun getPosts() {
        val request = VKRequest<Any>(newsfeed)
        request.addParam("return_banned", 0)
        request.addParam("count", 25)

        if (VK.isLoggedIn()) VK.execute(request, object : VKApiCallback<Any> {
            override fun fail(error: VKApiExecutionException) {
                Toast.makeText(presenter.view, "Ошибка загрузки данных", Toast.LENGTH_LONG). show()
            }

            override fun success(result: Any) {
                val obj: VkNewsFeedList.Response = Gson().fromJson(result.toString(), VkNewsFeedList.Response::class.java)
                presenter.view.onContentApplied(obj)

//                Logger.getLogger(VkInterceptor::class.java.name).warning(obj.response.groups.toString())
            }
        })
    }
}