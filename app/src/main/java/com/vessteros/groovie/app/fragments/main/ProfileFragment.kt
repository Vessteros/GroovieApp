package com.vessteros.groovie.app.fragments.main


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.fragments.Updater

class ProfileFragment : Fragment(), Updater {
    lateinit var v: View

    private lateinit var activity: ProfileEventListener

    lateinit var userfullbtn: Button
    lateinit var logoutbtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)

        init()

        return v
    }

    private fun init() {
        setViews()
        setOnClickListeners()
    }

    private fun setViews() {
        userfullbtn = v.findViewById(R.id.profileRedactor)
        logoutbtn = v.findViewById(R.id.logoutbtn)
    }

    private fun setOnClickListeners() {
        userfullbtn.setOnClickListener {
            it.isClickable = false
            activity.getFullUserInfo()
        }

        logoutbtn.setOnClickListener {
            it.isClickable = false
            activity.logout()
        }
    }

    override fun onResume() {
        super.onResume()

        userfullbtn.isClickable = true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activity = context as ProfileEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ProfileEventListener")
        }
    }

    override fun onUpdate() = (activity as MainActivity).presenter.profileWorker.update()

    interface ProfileEventListener {
        fun getFullUserInfo()
        fun logout()
    }
}
