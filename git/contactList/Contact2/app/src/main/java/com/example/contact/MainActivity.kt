package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ContactAdapter.OnItemClickListener {
   lateinit var  contacts: List<ContactDataClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting the layout to the Activity
        setContentView(R.layout.activity_main)
        //populating the recycler view with data to show on the main layout
        contacts = mutableListOf(
            ContactDataClass(
              R.drawable.cyril_ramaphosa,
                "President Cyril Ramaphosa",
                "cyril_Ramphosa@gmail.com",
                "2225-093356"
            ),
            ContactDataClass(
                R.drawable.hamad_bin_khalifa_al_thani,
                "Hamad bin Al-Thani",
                "hamadbinThani@outlook.com",
                "012-455728"
            ),
            ContactDataClass(
                R.drawable.president_george_weah,
                "President George Weah.",
                "President_George_Weah.@gmail.com",
                "221-5036758"
            ),
            ContactDataClass(
                R.drawable.president_barack_obama,
                "Prinsident Vladimir_Putin",
                "Vladimir_Putin@apple.com",
                "02-6067239"
            ),
            ContactDataClass(
                R.drawable.robert_mugabe,
                "President Robert Mugabe",
                "robert_mugabe@gmail.com",
                "203-9992176"
            ),
            ContactDataClass(
                R.drawable.chika_nwobi,
                "Dr. chika Nwobi",
                "chika_nwobi@decagon.institute",
                "2348033477809"
            ),
            ContactDataClass(
                R.drawable.president_barack_obama,
                "President Barack Obama",
                "barack_obama@apple.com",
                "401-7726566"
            ),
            ContactDataClass(
                R.drawable.muhammed_buhari,
                "President Muhammed Buhari",
                "muhammed_buhari@nigeria.gov.ng",
                "234-01-73547"
            ),
             ContactDataClass(
             R.drawable.aiony_haust,
            "Aiony Haust",
            "aiony_haust@model.com",
            "(01)735-47223"
            ),
            ContactDataClass(
                R.drawable.jehnej_graj,
                "Jehnej Graj",
                "jehrej_graj@model.com",
                "(01)735-47223"
            ),
            ContactDataClass(
                R.drawable.mahdi_chaghari,
                "Mahdi Chaghari",
                "mahdi_chaghari@model.com",
                "(01)722-41423"
            ),
            ContactDataClass(
                R.drawable.matheus_ferraro,
                "Matheus Ferraro",
                "matheus_ferraro@model.com",
                "(01)311-61423"
            ),
            ContactDataClass(
                R.drawable.michael_dam,
                "Michael Dam",
                "michael_dam@model.com",
                "(01)722-41423"
            ),
            ContactDataClass(
                R.drawable.nicole_geri,
                "Nicole Geri",
                "nicole_geri@model.com",
                "(01)213-01423"
            ),
            ContactDataClass(
                R.drawable.pooja_chaudhary,
                "Pooja Chaudhary",
                "pooja_chaudhary@model.com",
                "(02)555-01423"
            ),


        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ContactAdapter(contacts,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



    }

    override fun onItemClick(position: Int) {
     val intent = Intent(this,ViewContactInfo::class.java)
        intent.putExtra("name", contacts[position].name)
        intent.putExtra("mobile", contacts[position].phoneNumber)
        intent.putExtra("email", contacts[position].email)
        intent.putExtra("image", contacts[position].image)
        startActivity(intent)
    }
}
