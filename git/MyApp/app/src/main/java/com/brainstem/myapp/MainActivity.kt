package com.brainstem.myapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var countLandscape = 0
    var countPortrait = 0

    //val showText = findViewById<TextView>(R.id.showText)
    //val showCount = findViewById<TextView>(R.id.showCount)
    val handler = Handler(Looper.getMainLooper())

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("pCount", countPortrait)
        outState.putInt("lCount", countLandscape)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            countPortrait = savedInstanceState.getInt("pCount")
            countLandscape = savedInstanceState.getInt("lCount")
        }
        val button = findViewById<Button>(R.id.secondActivity)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)
        }
    }

         override fun onStart() {
             super.onStart()
             handler.postDelayed(
                 { showText.text = "Application is Starting [onStarting Mode]" },
                 1200)
         }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            countPortrait++
            showCount.text = "Portrait Count: $countPortrait"
        } else {
            countLandscape++
            showCount.text = "Lanscape Count: $countLandscape"
        }
        showText.text = "Application change orientation"
        handler.postDelayed(
            { showText.text = "Application is reStarting [onStarting Mode]" },
            1400)
    }

        override fun onRestart() {
            super.onRestart()
            handler.postDelayed(
                { showText.text = "Application is reStarting [onStarting Mode]" },
                400
            )
        }

        override fun onPause() {
            super.onPause()
            handler.postDelayed({ showText.text = "Application is Pausing [onPause Mode]" }, 700)
        }

        override fun onStop() {
            super.onStop()
            handler.postDelayed({ showText.text = "Application is Stopping [onStop Mode]" }, 500)
        }

        override fun onResume() {
            super.onResume()
            handler.postDelayed({ showText.text = "Application is on Resume Mode" }, 500)
        }

        override fun onBackPressed() {
            super.onBackPressed()
            handler.postDelayed({ showText.text = "Application is SOnBack Mode" }, 500)
        }

        override fun onDestroy() {
            super.onDestroy()
            handler.postDelayed({ showText.text = "Application is Destroy" }, 600)
        }
    }
