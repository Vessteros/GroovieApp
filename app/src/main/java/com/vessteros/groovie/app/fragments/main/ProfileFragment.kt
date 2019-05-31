package com.vessteros.groovie.app.fragments.main


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.fragments.Updater
import com.vessteros.groovie.app.models.cloud.responses.data.DataList
import com.vessteros.groovie.app.models.entities.User

class ProfileFragment : Fragment(), Updater {
    lateinit var v: View

    private lateinit var activity: ProfileEventListener

    private val worker
        get() = (activity as MainActivity).presenter.profileWorker

    private val userfullbtn: Button
        get() = v.findViewById(R.id.profileRedactor)

    private val logoutbtn: Button
        get() = v.findViewById(R.id.logoutbtn)

    private val userFirstName: TextView
        get() = v.findViewById(R.id.userFirstName)

    private val userLastName: TextView
        get() = v.findViewById(R.id.userLastName)

    private val userLogin: TextView
        get() = v.findViewById(R.id.userLogin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)
        (activity as MainActivity).presenter.profileWorker.attach(this)
        findUser()
        init()
        return v
    }

    private fun init() {
        setOnClickListeners()
    }

    private fun findUser() = worker.findUser()

    fun setView(userInfo: User) {
        userFirstName.text = userInfo.name
        userLastName.text = userInfo.lastName
        userLogin.text = userInfo.login
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
