package com.example.savecontacttodatabase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class DisplayContact : AppCompatActivity() {
    private val requestCode = 1
    var mobile = ""
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_contact)


        val backButton = findViewById<ImageButton>(R.id.backButton)
        val callBtn = findViewById<ImageButton>(R.id.imageButton3)
        val videoBtn = findViewById<ImageButton>(R.id.imageButton5)
        val messageBtn = findViewById<ImageButton>(R.id.imageButton8)
        val shareBtn = findViewById<ImageButton>(R.id.imageButton14)

        val firstname = intent.getStringExtra("firstname")
        val email = intent.getStringExtra("email")
        mobile = intent.getStringExtra("mobile")!!
        val lastname = intent.getStringExtra("lastname")

        findViewById<TextView>(R.id.textName).text = "$firstname $lastname"
        findViewById<TextView>(R.id.textView3).text = mobile
        findViewById<TextView>(R.id.email_text).text = email

        //share Button
        shareBtn.setOnClickListener{
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.ACTION_SEND,
                        "Share $firstname $$lastname $mobile $email"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
        }

        videoBtn.setOnClickListener{
            //val mobile = findViewById<TextView>(R.id.textView3).text
            val intentToVideoCall = Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA)
            intentToVideoCall.data = Uri.parse("video: $mobile")
            if (intentToVideoCall.resolveActivity(packageManager) != null) {
                startActivityForResult(intentToVideoCall, REQUEST_IMAGE_CAPTURE)
            }
        }

        //message send intent
        messageBtn.setOnClickListener{
            message()
        }

        //call intent
        callBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCode
                )
            } else {
                dialContact(mobile)
            }
        }

        //back button
        backButton.setOnClickListener {
            onBackPressed()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            dialContact(mobile)
    }

    //dial contact call function
    private fun dialContact(mobile: String) {
      //  val num = findViewById<TextView>(R.id.textView3).text.toString()
        val intentToCall = Intent(Intent.ACTION_CALL)
        Log.d("CALL", mobile)
        intentToCall.data = Uri.parse("tel: $mobile")
        startActivity(intentToCall)
    }

    //messaging function
    private fun message() {
        val msg = findViewById<TextView>(R.id.textView3).text
        val intentToMessage = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("sms: $msg")
        startActivity(intentToMessage)
    }
}

