package com.example.newt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.testing.R

class UserDataInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data_info)

        //User's Notification
      //  Toast.makeText(applicationContext,"Welcome to your profile", Toast.LENGTH_SHORT).show()

        //Associating views to variable
        var nameText = findViewById<TextView>(R.id.textName)
        var userNameText = findViewById<TextView>(R.id.textUserName)
        var emailText = findViewById<TextView>(R.id.textEmail)
        var mobileText = findViewById<TextView>(R.id.textMobile)
        var genderText = findViewById<TextView>(R.id.textGender)

        var intent = intent


        //Getting data stored from Intent
        var name = intent.getStringExtra("name")
        var username = intent.getStringExtra("username")
        var email = intent.getStringExtra("email")
        var mobile = intent.getStringExtra("mobile")
        var gender = intent.getStringExtra("gender")

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()


        // Displaying User's Profile Details
        nameText.text = name
        userNameText.text = username
        emailText.text = email
        mobileText.text = mobile
        genderText.text = gender
    }

}