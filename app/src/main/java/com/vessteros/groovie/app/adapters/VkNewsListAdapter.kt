package com.vessteros.groovie.app.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vessteros.groovie.app.models.cloud.responses.data.VkNewsFeedList

class VkNewsListAdapter(val content: VkNewsFeedList.Response, val context: Context) : RecyclerView.Adapter<VkNewsListAdapter.VkNewsHolder>() {
    private val dp = context.resources.displayMetrics.density

    private val groups
        get() = content.response.groups

    private val items
        get() = content.response.items

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VkNewsHolder =
        VkNewsHolder(LayoutInflater.from(p0.context).inflate(R.layout.fragment_news, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: VkNewsHolder, p1: Int) {
        val group = groups.find {
            it.id == -items[p1].source_id!!
        }!!

        val post = items[p1]

        p0.newsTitle.text = group.name
        p0.newsPostDate.text = post.date.toString()

        Glide.with(context)
            .asDrawable()
            .load(group.photo_200)
            .dontAnimate()
            .into(p0.newsImage)

        if (post.text != "") {
            p0.postText.text = post.text
            p0.postText.setPadding(24, (dp * 20).toInt(), 24, (dp * 10).toInt())
        }

        if (!post.attachments.isNullOrEmpty()) {
            val adapter = VkAttachmentsAdapter(context as VkActivity, post.attachments)

            p0.photoAttachments.adapter = adapter
            p0.photoAttachments.visibility = View.VISIBLE
        }
    }

    class VkNewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        val newsPostDate = itemView.findViewById<TextView>(R.id.newsPostDate)
        val newsBody = itemView.findViewById<ConstraintLayout>(R.id.newsBody)
        val postText = itemView.findViewById<TextView>(R.id.postText)
        val photoAttachments = itemView.findViewById<ViewPager>(R.id.photoAttachments)
    }
}