package com.vessteros.groovie.app.fragments.networks.vk


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.vessteros.groovie.R

class ChatListFragment : Fragment() {
    lateinit var v: View

    lateinit var meh: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_chat_list, container, false)

        meh = v.findViewById(R.id.meh)

        return v
    }


}
