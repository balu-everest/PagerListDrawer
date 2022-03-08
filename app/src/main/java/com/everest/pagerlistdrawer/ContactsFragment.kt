package com.everest.pagerlistdrawer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Contact(
    val name: String,
    val phoneNumber: Int
)

val contacts = listOf<Contact>(
    Contact("User1", 12314), //0,
    Contact("User2", 12314),
    Contact("User3", 12314),
    Contact("User4", 12314),
    Contact("User5", 12314),
    Contact("User6", 12314), //5
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314),
    Contact("User6", 12314)

)

class ContactsFragment : Fragment(R.layout.contacts_list_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactListRV = view.findViewById<RecyclerView>(R.id.contacts_list)

        contactListRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val contactsAdapter = ContactAdapter(contacts)
        contactListRV.adapter = contactsAdapter


    }

}