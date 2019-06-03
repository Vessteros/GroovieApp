package com.vessteros.groovie.app.activities.networks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.r0adkll.slidr.Slidr
import com.vessteros.groovie.R
import com.vessteros.groovie.app.adapters.VkAttachmentsAdapter
import com.vessteros.groovie.app.adapters.VkNewsListAdapter
import com.vessteros.groovie.app.models.cloud.responses.data.VkNewsFeedList
import com.vessteros.groovie.app.presenters.networks.VkPresenter
import kotlinx.android.synthetic.main.activity_vk.*


class VkActivity : AppCompatActivity() {
    private val presenter = VkPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vk)
        Slidr.attach(this)

        init()
    }

    private fun init() {
        presenter.getContent()
    }

    fun onContentApplied(content: VkNewsFeedList.Response) {
        newsList.adapter = VkNewsListAdapter(content, this)
        newsList.layoutManager = LinearLayoutManager(this)
    }

    fun moveOn(intent: Intent) = startActivity(intent)
}
