package com.vessteros.groovie.app.fragments.login


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.vessteros.groovie.R

class RegisterFragment : Fragment() {
    private lateinit var activity: RegisterEventListener

    lateinit var v: View

    lateinit var rgbtn: Button

    lateinit var login: EditText

    lateinit var pass: EditText

    lateinit var repass: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_register, container, false)

        init()

        return v
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activity = context as RegisterEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement RegisterEventListener")
        }
    }

    private fun init() {
        setViews()
        setClickListeners()
    }

    private fun setViews() {
        rgbtn = v.findViewById(R.id.rgbtn)
        login = v.findViewById(R.id.login)
        pass = v.findViewById(R.id.pass)
        repass = v.findViewById(R.id.repass)
    }

    private fun setClickListeners() {
        registerListener()
    }

    private fun registerListener() = rgbtn.setOnClickListener { activity.registerAction() }

    interface RegisterEventListener {
        fun registerAction()
    }
}
