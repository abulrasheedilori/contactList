package com.brainstem.dependencyinjectionclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import android.widget.Toast
import com.brainstem.dependencyinjectionclass.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Dependency Injection
//
//Implementation 1
//Make a network call to this web service : https://jsonplaceholder.typicode.com/users.
//Implement Dependency Injection, using any DI(dependency injection) library of
//your choice.

//Implementation 2
//In any project of  your choice where you made a network call.
//Implement Dependency Injection, using any DI(dependency injection) library of your choice.

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getUsers()
        viewModel.usersResponse.observe(this, {
            if (it.isSuccessful){
                textView = findViewById(R.id.testText)
                textView.text = it.body().toString()
                Log.d("Response", it.body().toString())

            }else{
                Toast.makeText(this, "UNSUCCESSFUL ${it.errorBody()}", Toast.LENGTH_SHORT).show()
            }
        })



    }
}