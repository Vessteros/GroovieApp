package com.vessteros.groovie.app.adapters

import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.fragments.main.NetworkUIFragment
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.fragments.main.SettingsFragment

class PagerAdapter(private val view: MainActivity): PagerAdapter() {
    private val fragmentList = arrayListOf(
        ProfileFragment(),
        NetworkUIFragment(),
        SettingsFragment()
    )

    private val layoutList = arrayListOf(
        R.layout.user_profile_layout,
        R.layout.networks_ui_layout,
        R.layout.settings_layout
    )

    private val fragmentIdList = arrayListOf(
        R.id.profileFragment,
        R.id.networksFragment,
        R.id.settingsFragment
    )

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as ConstraintLayout
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pageView = view.layoutInflater.inflate(layoutList[position], container, false)

        view.supportFragmentManager
            .beginTransaction()
            .add(fragmentIdList[position], fragmentList[position])
            .commit()

        container.addView(pageView, position)

        return pageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        view.supportFragmentManager
            .beginTransaction()
            .remove(fragmentList[position])
            .commit()

        container.removeView(`object` as ConstraintLayout)
    }
}