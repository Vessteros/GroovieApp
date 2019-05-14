package com.vessteros.groovie.app.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.app.adapters.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val adapter = PagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = adapter

//        textView.text = Realm
//            .getDefaultInstance()
//            .where(User::class.java)
//            .equalTo("isActive", true)
//            .findFirst()
//            .toString()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
