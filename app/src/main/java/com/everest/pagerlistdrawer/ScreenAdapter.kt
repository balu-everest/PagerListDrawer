package com.everest.pagerlistdrawer

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ScreenAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    val titles = listOf<String>(
        "Contacts",
        "Chats",
        "Calls"
    )

    override fun getCount(): Int = 3

    override fun getItem(position: Int) =
        when (position) {
            0 -> ContactsFragment()
            1 -> ChatsFragment()
            2 -> CallsFragment()
            else -> error("Position does not exists")
        }

    override fun getPageTitle(position: Int) = titles[position]
}