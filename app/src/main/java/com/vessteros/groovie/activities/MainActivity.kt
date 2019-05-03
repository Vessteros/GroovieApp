package com.vessteros.groovie.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.issues.Issue
import com.vessteros.groovie.presenters.BasePresenter
import com.vessteros.groovie.presenters.MainPresenter

class MainActivity : AppCompatActivity(), IRenderActivity {
    override fun <I : Issue<I>> issue(issue: I) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // на будущее
    private val presenter: BasePresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val view = this

        val intent = Intent(view, LRActivity::class.java)
        startActivity(intent)
    }
}
