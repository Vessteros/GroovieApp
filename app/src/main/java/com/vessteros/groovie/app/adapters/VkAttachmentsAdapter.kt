package com.vessteros.groovie.app.adapters

import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.networks.VkActivity
import com.vessteros.groovie.app.models.cloud.responses.data.VkNewsFeedList
import kotlinx.android.synthetic.main.photo_attachment_layout.view.*

class VkAttachmentsAdapter(
    val view: VkActivity,
    private val attachments: ArrayList<VkNewsFeedList.Attachment>
) : PagerAdapter() {
    private val photos
        get() = attachments.filter {
            it.type == "photo"
        }.map {
            it.photo?.sizes?.find {
                it.type == "r"
            }?.url
        }

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1 as ConstraintLayout

    override fun getCount(): Int = photos.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pageView = view.layoutInflater.inflate(R.layout.photo_attachment_layout, container, false)

        Glide.with(view)
            .asDrawable()
            .load(photos[position])
            .dontAnimate()
            .into(pageView.attachmentPhoto)

        container.addView(pageView, position)
        return pageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as ConstraintLayout)
}