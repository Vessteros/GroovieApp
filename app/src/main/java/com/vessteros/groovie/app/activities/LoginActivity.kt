package com.vessteros.groovie.app.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.app.fragments.login.*
import com.vessteros.groovie.app.models.cases.LoginCase
import com.vessteros.groovie.app.models.cases.RegisterCase
import com.vessteros.groovie.app.presenters.LoginPresenter

class LoginActivity : AppCompatActivity(), IRenderActivity, LoginFragment.LoginEventListener, RegisterFragment.RegisterEventListener {
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()

    private val presenter: LoginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun init() {
        setDefaultFragment()
    }

    private fun setDefaultFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.loginFragment, loginFragment)
            .addToBackStack(null)
            .commit()
    }

    fun moveOn() = startActivity(Intent(this, MainActivity::class.java))

    /********************************* LoginEventListener *********************************/
    override fun showRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragment, registerFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun loginAction() = presenter.loginAction(
        LoginCase(
            loginFragment.login.text.toString(),
            loginFragment.pass.text.toString()
        )
    )
    /********************************* LoginEventListener *********************************/

    /******************************* RegisterEventListener ********************************/
    override fun registerAction() = presenter.registerAction(
        RegisterCase(
            registerFragment.login.text.toString(),
            registerFragment.pass.text.toString(),
            registerFragment.repass.text.toString()
        )
    )
    /******************************* RegisterEventListener ********************************/
}
