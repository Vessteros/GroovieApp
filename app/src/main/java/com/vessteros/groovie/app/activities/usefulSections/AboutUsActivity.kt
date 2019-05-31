package com.vessteros.groovie.app.activities.usefulSections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.r0adkll.slidr.Slidr
import com.vessteros.groovie.R

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        Slidr.attach(this)
    }
}
