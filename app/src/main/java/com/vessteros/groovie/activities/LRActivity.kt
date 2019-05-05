package com.vessteros.groovie.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.issues.Issue
import com.vessteros.groovie.presenters.LoginPresenter
import kotlinx.android.synthetic.main.activity_lr.*

class LRActivity : AppCompatActivity(), IRenderActivity {

    /*************************** LifeCycle ***************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lr)

        init()
    }

    /*************************** LifeCycle ***************************/

    /************************* CustomMethods *************************/
    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        setLoginListener()
        setRegisterListener()
    }

    // теоритически через фрагменты сделать все в одной Activity, но пока слишком лень разбираться
    private fun setLoginListener() {
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRegisterListener() {
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    /************************* CustomMethods *************************/
}
