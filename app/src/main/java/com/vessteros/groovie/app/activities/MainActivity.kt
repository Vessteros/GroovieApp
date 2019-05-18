package com.vessteros.groovie.app.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import android.view.View
import android.view.Window
import android.widget.Button
import com.vessteros.groovie.R
import com.vessteros.groovie.app.adapters.PagerAdapter
import com.vessteros.groovie.app.fragments.main.NetworkUIFragment
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity(), ProfileFragment.ProfileEventListener,
    NetworkUIFragment.NetworkUIEventListener {

    val adapter = PagerAdapter(this)

    val presenter = MainPresenter(this)

    val currentFragment: Fragment
        get() = adapter.fragmentList[pager.currentItem]

    private lateinit var menuList: ArrayList<Button>

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

        override fun onPageSelected(i: Int) {
            addMenuIndicator(i)
        }

        override fun onPageScrollStateChanged(i: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuList = arrayListOf(
            profile,
            networks,
            settings
        )

        pager.adapter = adapter
        pager.addOnPageChangeListener(viewListener)

        menuList[pager.currentItem].apply {
            setBackgroundColor(resources.getColor(R.color.colorAccentHelper))
            setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }

        setOnClickListeners()
        setSwiper()
    }

    private fun setSwiper() {
        swiper.setOnRefreshListener {
            adapter.updatePage(pager.currentItem)
            swiper.isRefreshing = false
        }
    }


    private fun setOnClickListeners() {
        profile.setOnClickListener {
            pager.currentItem = 0
        }

        networks.setOnClickListener {
            pager.currentItem = 1
        }

        settings.setOnClickListener {
            pager.currentItem = 2
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun addMenuIndicator(position: Int) = menuList.forEach {
        it.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        it.setTextColor(resources.getColor(R.color.colorAccentHelper))
    }.also {
        menuList[position].apply {
            setBackgroundColor(resources.getColor(R.color.colorAccentHelper))
            setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
    }

    fun moveOn(intent: Intent) {
        startActivity(intent)
    }

    /********************************* ProfileEventListener *********************************/

    override fun getFullUserInfo() {
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.putExtra("userFirstName", userFirstName.text)
        intent.putExtra("userLastName", userLastName.text)
        intent.putExtra("userLogin", userLogin.text)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair<View, String>(findViewById(R.id.userPhoto), getString(R.string.transition_user_photo)),
            Pair<View, String>(findViewById(R.id.userLastName), getString(R.string.transition_user_last_name)),
            Pair<View, String>(findViewById(R.id.userFirstName), getString(R.string.transition_user_first_name)),
            Pair<View, String>(findViewById(R.id.userLogin), getString(R.string.transition_user_login))
        ).toBundle()

        startActivity(intent, options)
    }

    override fun logout() {
        presenter.profileWorker.logout()
    }

    /********************************* ProfileEventListener *********************************/

    /******************************** NetworkUIEventListener ********************************/
    override fun startNetworkService(networkId: String) {
        presenter.networkWorker.startNetworkService(networkId)
    }

    /******************************** NetworkUIEventListener ********************************/
}
