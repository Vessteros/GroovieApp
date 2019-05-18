package com.vessteros.groovie.app.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.app.presenters.StartPresenter

class StartActivity : AppCompatActivity() {

    private val presenter = StartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter.checkActiveUser()
    }

    fun moveOn(intent: Intent) {
        startActivity(intent)
    }
}
