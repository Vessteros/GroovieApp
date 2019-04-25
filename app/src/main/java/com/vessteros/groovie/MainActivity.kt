package com.vessteros.groovie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.presenters.MainPresenter
import com.vessteros.groovie.views.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val view = this

        move.run {
            setOnClickListener{
                val intent = Intent(view, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
