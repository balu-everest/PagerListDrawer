package com.everest.pagerlistdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat.START
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.everest.pagerlistdrawer.ui.User
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var actionBarToggle: ActionBarDrawerToggle
    lateinit var mainScreenPager: ViewPager
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScreenPager = findViewById(R.id.home_screen_pager)
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

        setUpNavigationDrawer()
        setUpNavigationView()

    }

    private fun setUpNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(START)
            handleMenuItem(menuItem)
        }
    }

    private fun setUpNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu) //Adds Right side menu icon
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuItemHandled = handleMenuItem(item)
        if (menuItemHandled) return true
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleMenuItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_contacts -> {
                setCurrentPage(HomeScreen.CONTACTS)
                return true
            }
            R.id.nav_chats -> {
                setCurrentPage(HomeScreen.CHATS)
                return true
            }
            R.id.nav_calls -> {
                setCurrentPage(HomeScreen.CALLS)
                return true
            }
        }
        return false
    }

    private fun setCurrentPage(homeScreen: HomeScreen) {
        mainScreenPager.currentItem = homeScreen.id
    }
}