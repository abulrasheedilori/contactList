package com.example.savecontacttodatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.savecontacttodatabase.contact.ContactData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactRecyclerViewAdapter(
    private val contactList: ArrayList<ContactData>,
    private val listener: OnItemClickListen?,
    private val listener2: OnLongClickListen?

    ) : RecyclerView.Adapter<ContactRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var dbref : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item_view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return MyViewHolder(item_view)
    }

    inner class MyViewHolder(contact_item: View) : RecyclerView.ViewHolder(contact_item),
        View.OnClickListener,
        View.OnLongClickListener {
        val name: TextView = contact_item.findViewById<TextView>(R.id.name)
        val letterIcon: TextView = contact_item.findViewById<TextView>(R.id.first_letter)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {

            if (adapterPosition!= RecyclerView.NO_POSITION) {
                listener?.onItemClick(adapterPosition, p0)
            }
        }

        override fun onLongClick(p0: View?): Boolean {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener2?.onLongItemClick(adapterPosition, p0)
            }
            return false
        }

    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.apply {
                name.text = contactList[position].firstName + " " + contactList[position].lastName
                letterIcon.text = contactList[position].firstLetter
            }
        }

        override fun getItemCount(): Int {
            return contactList.size
        }

    interface OnItemClickListen {
        fun onItemClick(position: Int, myView: View?)
    }
    interface OnLongClickListen {
        fun onLongItemClick(position: Int, myView: View?)
    }
    fun deleteItem(i:Int){
        contactList.removeAt(i)
        notifyDataSetChanged()
    }
    fun deleteItem(i:Int, contact:ContactData){
        contactList.add(i, contact)
        notifyDataSetChanged()
    }
    //Deleting contact

     fun deleteContact(id:String, context: Context): Boolean {
        var result = true
        dbref = FirebaseDatabase.getInstance().getReference("Contact")
        dbref.child(id).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_LONG).show()
            result = true
        }.addOnFailureListener {
            result = false
        }
        return result
    }

    fun getContactWithPosition(position: Int): ContactData = contactList[position]
}