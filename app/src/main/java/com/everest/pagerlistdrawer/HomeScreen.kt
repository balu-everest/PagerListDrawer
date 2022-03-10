package com.everest.pagerlistdrawer

sealed class HomeScreen(val id: Int) {
    object CONTACTS : HomeScreen(0)
    object CHATS : HomeScreen(1)
    object CALLS : HomeScreen(2)
}