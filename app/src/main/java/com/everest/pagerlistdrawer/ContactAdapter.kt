package com.everest.pagerlistdrawer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Mutablity var
//Immutability val
class ContactAdapter (val contacts: List<Contact>): RecyclerView.Adapter<ContactAdapter.ContactItemViewHolder>() {

    val TAG = "ContactAdapter"

    inner class ContactItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTV = view.findViewById<TextView>(R.id.contact_name_tv)
        val phoneTV = view.findViewById<TextView>(R.id.contact_number_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        Log.d(TAG, "onCreateViewHolder")
       return ContactItemViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
       )
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder position: $position")
        val contact = contacts[position]
        holder.nameTV.text = contact.name
        holder.phoneTV.text = "${contact.phoneNumber}"
    }

    override fun getItemCount(): Int = contacts.size
}