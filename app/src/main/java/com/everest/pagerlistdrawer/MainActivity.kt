package com.everest.pagerlistdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.everest.pagerlistdrawer.ui.User
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var actionBarToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainScreenPager = findViewById<ViewPager>(R.id.home_screen_pager)
        val adapter = ScreenAdapter(supportFragmentManager)
        mainScreenPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.home_screen_tab_layout)
        tabLayout.setupWithViewPager(mainScreenPager)

        val viewModel = ViewModelProvider(this)[CustomViewModel::class.java]
        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                UIState.Loading -> showLoadingScreen()
                is UIState.Data -> showListOfUsers(uiState.data)
                is UIState.Error -> showErrorMessage(uiState.message)
            }

        }

        handleNavigationDrawer()

    }

    private fun handleNavigationDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showLoadingScreen() {
        Log.d(TAG, "showLoadingScreen")
    }

    private fun showErrorMessage(message: String) {
        Log.d(TAG, "showErrorMessage : $message")
    }

    private fun showListOfUsers(data: List<User>) {
        Log.d(TAG, "showListOfUsers : ${Gson().toJson(data)}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}