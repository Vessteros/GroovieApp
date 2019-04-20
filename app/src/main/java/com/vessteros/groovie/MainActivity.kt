package com.vessteros.groovie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.presenters.MainPresenter

class MainActivity : AppCompatActivity() {
    val presenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}
