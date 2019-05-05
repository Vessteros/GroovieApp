package com.vessteros.groovie.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.presenters.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), IRenderActivity {

    private val presenter: LoginPresenter = LoginPresenter(this)

    /*************************** LifeCycle ***************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.dbWorker.exterminate()
    }

    /*************************** LifeCycle ***************************/

    /************************* CustomMethods *************************/
    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        setLoginListener()
        setFieldListeners()
    }

    // теоритически через фрагменты сделать все в одной Activity, но пока слишком лень разбираться
    private fun setLoginListener() {
        register.setOnClickListener {
            presenter.loginAction()
        }
    }

    private fun setFieldListeners() {
        login.setOnClickListener {
            dispel()
        }

        password.setOnClickListener {
            dispel()
        }
    }

    fun moveOn(intent: Intent) {
        startActivity(intent)
    }

    /**
     * todo: снимать выделение при ошибке
     */
    @SuppressLint("ResourceAsColor")
    private fun dispel() {
        login.setTextColor(R.color.white)
        login.setBackgroundColor(R.color.white)

        password.setTextColor(R.color.white)
        password.setBackgroundColor(R.color.white)
    }

    /************************* CustomMethods *************************/
}
