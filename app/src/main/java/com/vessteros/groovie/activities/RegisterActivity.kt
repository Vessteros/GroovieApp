package com.vessteros.groovie.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.issues.Issue

class RegisterActivity : AppCompatActivity(), IRenderActivity {
    override fun <I : Issue<I>> issue(issue: I) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}
