package com.brainstem.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    //creating instance of supportFragmentManager
    val fragment = supportFragmentManager
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //add fragment

        val addButton = findViewById<Button>(R.id.addBtn)
        addButton.setOnClickListener {
            addFragment()
            count++
            Toast.makeText(this,"This is $count++ Fragment", Toast.LENGTH_SHORT)
        }

        //remove fragment
        val removeButton = findViewById<Button>(R.id.removeBtn)
        removeButton.setOnClickListener {
            removeFragment()

        }

    }

    //Add fragment function
    fun addFragment() {
        val fragManager = supportFragmentManager
        fragManager.beginTransaction().add(
            R.id.fragment,
            Fragment1.newInstance(count.toString(), ""), null
        )
            .addToBackStack(null)
            .commit()
    }

    //remove fragment function
    fun removeFragment() {
        fragment.popBackStack()
        count--
    }
}



