package com.vessteros.groovie.app.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.view.View
import android.view.Window
import com.r0adkll.slidr.Slidr
import com.vessteros.groovie.R
import com.vessteros.groovie.app.adapters.ProfileRecycleViewAdapter
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.userFirstName
import kotlinx.android.synthetic.main.activity_user_profile.userLastName
import kotlinx.android.synthetic.main.activity_user_profile.userLogin
import kotlinx.android.synthetic.main.fragment_profile.*

class UserProfileActivity : AppCompatActivity() {
    private val iconList = arrayListOf(
        R.drawable.faq,
        R.drawable.faq,
        R.drawable.faq,
        R.drawable.faq
    )

    private val titleList = arrayListOf(
        "Написать обращение",
        "Оставить отзыв",
        "О Нас",
        "FAQ"

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        userFirstName.hint = intent.getStringExtra("userFirstName")
        userLastName.hint = intent.getStringExtra("userLastName")

        userLogin.hint = intent.getStringExtra("userLogin")

        Slidr.attach(this)

        init()
    }

    private fun init() {
        setOnClickListeners()
        initRecycleView()
    }

    private fun initRecycleView() {
        faqList.adapter = ProfileRecycleViewAdapter(iconList, titleList, this)
        faqList.layoutManager = LinearLayoutManager(this)
    }

    private fun setOnClickListeners() {
        save.setOnClickListener {
            returnToMain()
        }
    }

    private fun returnToMain() {
        val intent = Intent(this, MainActivity::class.java)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair<View, String>(findViewById(R.id.userPhoto), getString(R.string.transition_user_photo)),
            Pair<View, String>(findViewById(R.id.userLastName), getString(R.string.transition_user_last_name)),
            Pair<View, String>(findViewById(R.id.userFirstName), getString(R.string.transition_user_first_name)),
            Pair<View, String>(findViewById(R.id.userLogin), getString(R.string.transition_user_login))
        ).toBundle()

        startActivity(intent, options)
    }
}
