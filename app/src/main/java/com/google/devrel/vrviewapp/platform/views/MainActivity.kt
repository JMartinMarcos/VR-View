package com.google.devrel.vrviewapp.platform.views

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.google.devrel.vrviewapp.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Jorge MM on 24/5/18.
 */

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolBar)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.welcome))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.venue))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        pager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment? {
                when (position) {
                    0 -> return PanoramicFragment()
                    1 -> return VrVideoViewFragment()
                }
                return null
            }
            override fun getCount(): Int {
                return 2
            }
        }

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

    }
    override fun getLayoutId() : Int {
        return R.layout.activity_main
    }
}