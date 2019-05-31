package com.vessteros.groovie.app.fragments.networks


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.vessteros.groovie.R

class NewsFragment : Fragment() {
    lateinit var v: View

    lateinit var newsTitle: TextView

    lateinit var newsImage: ImageView

    lateinit var newsPostDate: TextView

    lateinit var newsBody: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_news, container, false)

        init()

        return v
    }

    private fun init() {
        setViews()
    }

    private fun setViews() {

    }


}
