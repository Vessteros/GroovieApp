package com.vessteros.groovie.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vessteros.groovie.R
import com.vessteros.groovie.entities.GUser
import com.vessteros.groovie.models.db.DBWorker
import com.vessteros.groovie.models.db.entities.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IRenderActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbWorker = DBWorker()

        val result: User? = dbWorker.realm.where(User::class.java).equalTo("id", GUser.id).findFirst()

        result?.let {
            textView.text = result.toString()
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
