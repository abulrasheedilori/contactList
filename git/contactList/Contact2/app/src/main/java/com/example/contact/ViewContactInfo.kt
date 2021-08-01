package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ViewContactInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact_info)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")
        val image = intent.extras?.getInt("image")

        findViewById<TextView>(R.id.textName).text = name
        findViewById<TextView>(R.id.textView3).text = mobile
        findViewById<TextView>(R.id.email_text).text = email
        if (image != null) {
            findViewById<ImageView>(R.id.imageView).setImageResource(image)
        }

        //back button
        backButton.setOnClickListener {
            onBackPressed()

        }
    }
}