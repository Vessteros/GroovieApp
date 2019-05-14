package com.vessteros.groovie.app.fragments.login


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.vessteros.groovie.R

class LoginFragment : Fragment() {
    private lateinit var activity: LoginEventListener

    lateinit var v: View

    lateinit var rgbtn: Button

    lateinit var lgbtn: Button

    lateinit var login: EditText

    lateinit var pass: EditText

    /**
     * Создание фрагмента
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        init()

        return v
    }

    /**
     * При монтировании фрагмента в активити
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activity = context as LoginEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement LoginEventListener")
        }
    }

    /**
     * Инициализация фрагмента
     */
    private fun init() {
        setViews()
        setClickListeners()
    }

    /**
     * Инициализация UI элементов
     */
    private fun setViews() {
        rgbtn = v.findViewById(R.id.rgbtn)
        lgbtn = v.findViewById(R.id.lgbtn)
        login = v.findViewById(R.id.login)
        pass = v.findViewById(R.id.pass)
    }

    /**
     * Click-лисенеры
     */
    private fun setClickListeners(): Unit = run {
        registerListener()

        loginListener()
    }

    private fun registerListener(): Unit = rgbtn.setOnClickListener { activity.showRegisterFragment() }

    private fun loginListener(): Unit = lgbtn.setOnClickListener { activity.loginAction() }

    /**
     * Интерфейс для связи фрагмента с активити
     */
    interface LoginEventListener {
        fun showRegisterFragment()

        fun loginAction()
    }
}
