package com.everest.pagerlistdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainScreenPager = findViewById<ViewPager>(R.id.home_screen_pager)
        val adapter = ScreenAdapter(supportFragmentManager)
        mainScreenPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.home_screen_tab_layout)
        tabLayout.setupWithViewPager(mainScreenPager)
    }
}