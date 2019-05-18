package com.vessteros.groovie.app.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vessteros.groovie.R

class ProfileRecycleViewAdapter(
    private val iconList: ArrayList<Int>,
    private val titleList: ArrayList<String>,
    val context: Context
) : RecyclerView.Adapter<ProfileRecycleViewAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProfileViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.faq_item, p0, false)

        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return iconList.size
    }

    override fun onBindViewHolder(p0: ProfileViewHolder, p1: Int) {
        Glide.with(context)
            .asDrawable()
            .load(iconList[p1])
            .into(p0.icon)

        p0.title.text = titleList[p1]

        p0.item.setOnClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
        }
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: ConstraintLayout = itemView.findViewById(R.id.faqItem)
        val icon: ImageView = itemView.findViewById(R.id.faqItemIcon)
        val title: TextView = itemView.findViewById(R.id.faqItemTitle)
    }
}