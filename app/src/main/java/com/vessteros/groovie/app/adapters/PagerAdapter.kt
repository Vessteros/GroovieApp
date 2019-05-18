package com.vessteros.groovie.app.adapters

import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vessteros.groovie.R
import com.vessteros.groovie.app.activities.MainActivity
import com.vessteros.groovie.app.fragments.main.NetworkUIFragment
import com.vessteros.groovie.app.fragments.main.ProfileFragment
import com.vessteros.groovie.app.fragments.main.SettingsFragment

class PagerAdapter(private val view: MainActivity) : PagerAdapter() {
    val fragmentList: ArrayList<Fragment> = arrayListOf(
        ProfileFragment(),
        NetworkUIFragment(),
        SettingsFragment()
    )

    val layoutList = arrayListOf(
        R.layout.user_profile_layout,
        R.layout.networks_ui_layout,
        R.layout.settings_layout
    )

    val fragmentIdList = arrayListOf(
        R.id.profileFragment,
        R.id.networksFragment,
        R.id.settingsFragment
    )

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1 as ConstraintLayout

    override fun getCount(): Int = fragmentList.count()

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

    fun updatePage(position: Int) {
        view.supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .detach(fragmentList[position])
            .attach(fragmentList[position])
            .commit()
    }
}