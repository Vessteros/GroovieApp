package com.vessteros.groovie.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.issues.Issue
import com.vessteros.groovie.presenters.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), IRenderActivity {

    private val presenter: RegisterPresenter = RegisterPresenter(this)

    /*************************** LifeCycle ***************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.dbWorker.exterminate()
    }

    /*************************** LifeCycle ***************************/

    /************************* EventMethods **************************/
    //
    /************************* EventMethods **************************/

    /************************* CustomMethods *************************/

    private fun init() {
        setListeners()
    }

    private fun setListeners() {
        setRegisterListener()
    }

    private fun setRegisterListener() {
        register.setOnClickListener {
            presenter.registerAction()
        }
    }

    fun moveOn(intent: Intent) {
        startActivity(intent)
    }

    /************************* CustomMethods *************************/
}
