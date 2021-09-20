package com.example.readmycontact

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val requestCode = 1
    lateinit var searchView: SearchView
    lateinit var permissionBtn: ImageButton
    lateinit var statusView: TextView

    var cols = listOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.searchView)
        permissionBtn = findViewById(R.id.imageButton)
        statusView = findViewById(R.id.textView)

            permissionBtn.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_CONTACTS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        Array(1) { Manifest.permission.READ_CONTACTS },
                        requestCode
                    )
                } else {
                    statusView.text = "Permission Granted"
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                    readContact()
                }
            }
    }

    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show()
            readContact()
        }
    }

    //function to read contact
    private fun readContact(){
        val query = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    cols, null, null,
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        val from = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
        )

        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        val list_adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, query, from, to)

        val list_view = findViewById<ListView>(R.id.ListView)
        list_view.adapter = list_adapter

        //search bar implementation
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val query = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    cols, "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE?", Array(1){"%$p0%"},
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                list_adapter.changeCursor(query)
                return false
            }
        })
    }
}