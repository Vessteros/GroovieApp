package com.vessteros.groovie.app.activities.usefulSections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.r0adkll.slidr.Slidr
import com.vessteros.groovie.R

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        Slidr.attach(this)
    }
}
