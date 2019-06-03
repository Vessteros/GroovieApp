package com.vessteros.groovie.app.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.models.cloud.responses.data.DataList
import com.vessteros.groovie.app.models.cloud.responses.data.VkUser

class ConnectedNetworksListAdapter(
    val context: Context,
    private val connectedNetworkList: ArrayList<DataList.ConnectedNetwork>
) :
    RecyclerView.Adapter<ConnectedNetworksListAdapter.ConnectedNetworkViewHolder>() {

    val interceptor
        get() = (context as MainActivity).presenter.profileWorker.interceptor

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ConnectedNetworkViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.connected_network_layout, p0, false)

        return ConnectedNetworkViewHolder(view)
    }

    override fun getItemCount(): Int = connectedNetworkList.size

    override fun onBindViewHolder(p0: ConnectedNetworkViewHolder, p1: Int) {
        interceptor.getVkUser(this, p0)
    }

    @SuppressLint("SetTextI18n")
    fun onUserFound(
        userList: ArrayList<VkUser.UserList>,
        p0: ConnectedNetworkViewHolder
    ) {
        p0.connectedNetworkTitle.text = "${userList[0].first_name} ${userList[0].last_name}"
        p0.connectedNetworkStatus.text = userList[0].photo_400_orig
        Glide.with(context)
            .asDrawable()
            .load(userList[0].photo_400_orig)
            .into(p0.connectedNetworkIcon)
    }

    class ConnectedNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val connectedNetworkIcon = itemView.findViewById<ImageView>(R.id.connectedNetworkIcon)
        val connectedNetworkTitle = itemView.findViewById<TextView>(R.id.connectedNetworkTitle)
        val connectedNetworkStatus = itemView.findViewById<TextView>(R.id.connectedNetworkStatus)
    }
}