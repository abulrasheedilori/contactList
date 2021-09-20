package com.example.savecontacttodatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.savecontacttodatabase.contact.ContactData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay

class MainActivity3 : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var mobileNumber: EditText
    lateinit var emailAddress: EditText
    lateinit var saveBtn: Button
    lateinit var backImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        firstname = findViewById<EditText>(R.id.editText_FirstName)
        lastname = findViewById<EditText>(R.id.editText_LastName)
        mobileNumber = findViewById<EditText>(R.id.editText_Mobile)
        emailAddress = findViewById<EditText>(R.id.editText_Email)
        //statusBox = findViewById<EditText>(R.id.status)
        saveBtn = findViewById<Button>(R.id.saveContactBtn)
        backImageButton = findViewById<ImageButton>(R.id.imageBackButton)


        saveBtn.setOnClickListener {
            saveContact()
        }

        backImageButton.setOnClickListener {
            val goBackIntent = Intent(this,MainActivity::class.java)
            startActivity(goBackIntent)
        }
    }

    private fun saveContact() {
        val firstName = firstname.text.toString().trim()
        val lastName = lastname.text.toString().trim()
        val mobile = mobileNumber.text.toString().trim()
        val email = emailAddress.text.toString().trim()
        val firstLetter = firstName.first().toString().trim()

        if(firstName.isEmpty() && mobile.isEmpty()){
            firstname.error = "Input your first name"
            mobileNumber.error = "Input your phone number"
            return
        }
        if (firstName.isEmpty()) {
            firstname.error = "Input your first name"
            return
        }
        if(mobile.isEmpty()){
            mobileNumber.error = "Input your phone number"
            return
        }
        val firebaseInst = FirebaseDatabase.getInstance().getReference("Contact")
        val contactId = firebaseInst.push().key
        val contactInst =
            contactId?.let { ContactData(it, firstName, firstLetter, lastName, mobile, email) }
        if (contactId != null) {
            firebaseInst.child(contactId).setValue(contactInst).addOnCompleteListener{
                saveBtn.text="Saved Successfully"

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
