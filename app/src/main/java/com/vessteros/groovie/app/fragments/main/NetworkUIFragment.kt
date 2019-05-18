package com.vessteros.groovie.app.fragments.main


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.fragments.Updater

class NetworkUIFragment : Fragment(), Updater {
    lateinit var v: View

    private lateinit var activity: NetworkUIEventListener

    lateinit var vkbtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_network_ui, container, false)

        init()

        return v
    }

    private fun init() {
        setViews()
        setOnClickListeners()
    }

    private fun setViews() {
        vkbtn = v.findViewById(R.id.vkbtn)
    }

    private fun setOnClickListeners() {
        vkbtn.setOnClickListener {
            activity.startNetworkService("vk")
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activity = context as NetworkUIEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement NetworkUIEventListener")
        }
    }

    override fun onUpdate() = (activity as MainActivity).presenter.networkWorker.update()

    interface NetworkUIEventListener {
        fun startNetworkService(networkId: String)
    }
}
