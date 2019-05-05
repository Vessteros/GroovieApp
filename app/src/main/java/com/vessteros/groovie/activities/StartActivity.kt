package com.vessteros.groovie.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.issues.Issue
import com.vessteros.groovie.presenters.BasePresenter
import com.vessteros.groovie.presenters.MainPresenter
import com.vessteros.groovie.presenters.StartPresenter

class StartActivity : AppCompatActivity(), IRenderActivity {

    private val presenter: StartPresenter = StartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        presenter.checkUserActive()
    }

    fun moveOn(intent: Intent) {
        startActivity(intent)
    }

    override fun <I : Issue<I>> issue(issue: I) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
