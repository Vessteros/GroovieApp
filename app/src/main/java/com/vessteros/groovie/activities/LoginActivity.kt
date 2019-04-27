package com.vessteros.groovie.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.presenters.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val presenter: LoginPresenter = LoginPresenter(this)

    /*************************** LifeCycle ***************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    /*************************** LifeCycle ***************************/

    /************************* CustomMethods *************************/
    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        setLoginListener()
    }

    // теоритически через фрагменты сделать все в одной Activity, но пока слишком лень разбираться
    private fun setLoginListener() {
        logIn.setOnClickListener {
            presenter.loginAction()
        }
    }
    /************************* CustomMethods *************************/
}
