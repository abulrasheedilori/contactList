package com.example.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.newt.UserDataInfo

import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var gender = arrayOf<String?>("male", "female")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        val spin = findViewById<View>(R.id.spinner) as Spinner
        spin.onItemSelectedListener = this

        //Creating the ArrayAdapter instance having the gender list
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, gender)
        aa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //Setting the ArrayAdapter data on the Spinner
        spin.adapter = aa


        //getting the id of the register button
        val registerButton = findViewById<Button>(R.id.register)

        //setting action to perform when button is clicked
        registerButton.setOnClickListener {
            //creating variable of our editTextView
            val name = findViewById<EditText>(R.id.name).text.toString().trim()
            val userName = findViewById<EditText>(R.id.userName).text.toString().trim()
            val email = findViewById<EditText>(R.id.Email).text.toString().trim()
            val mobile = findViewById<EditText>(R.id.mobile).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()
            val confirmedPassword = findViewById<EditText>(R.id.confirmedPassword).text.toString().trim()



            //validating received data after collecting their values

            validate(name, userName, email, mobile, password, confirmedPassword, gender)
        }
    }
    //Performing action onItemSelected and onNothing selected
//    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View, position: Int, id: Long) {
//        //Toast.makeText(applicationContext, gender[position], Toast.LENGTH_LONG).show()
//        val gender = gender[position]

       override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            // On selecting a spinner item
            val gender: String = parent.getItemAtPosition(position).toString()
            // Showing selected spinner item
//            Toast.makeText(
//                parent.getContext(), "You selected: $gender",
//                Toast.LENGTH_SHORT
//            ).show()
        }


    override fun onNothingSelected(arg0: AdapterView<*>?) {
        // TODO Auto-generated method stub
    }


    private fun validate(
        name:String, userName:String, email:String, mobile:String,
        password:String, confirmedPassword:String, gender: Array<String?>
    ){

       if(email.isEmpty() || !email.contains("@") || (!email.contains("."))){
           findViewById<TextInputLayout>(R.id.emailLabel).error = "Input a valid email"
        }else if(name.isEmpty()) {
           findViewById<EditText>(R.id.name).error = "Input your full name"
       }else if (userName.length < 4 || userName.isEmpty()) {
           findViewById<TextInputLayout>(R.id.userNameLabel).error = "Username should be more than four characters"
       }else if(mobile.length < 7){
           findViewById<TextInputLayout>(R.id.mobileLabel).error = "Include country codes as prefix"
       }else if(password.isEmpty() || password !== confirmedPassword || password.length < 6){
           findViewById<TextInputLayout>(R.id.passwordLabel).error = "Password should match, not empty and be more than 5"
       }else if(gender.isEmpty()) {
            findViewById<TextInputLayout>(R.id.genderLabel).error = "Select your sex"
       }else {
           //notification of success
           Toast.makeText(applicationContext,"$name, Your profile is successfully created", Toast.LENGTH_SHORT).show()

           //sending user message to display view
           val sendUserFormDataIntent = Intent(this, UserDataInfo::class.java)

           sendUserFormDataIntent.putExtra("name", name)
           sendUserFormDataIntent.putExtra("username", userName)
           sendUserFormDataIntent.putExtra("email", email)
           sendUserFormDataIntent.putExtra("mobile", mobile)
           sendUserFormDataIntent.putExtra("password", password)
           sendUserFormDataIntent.putExtra("confirmedPassword", confirmedPassword)
           sendUserFormDataIntent.putExtra("gender", gender)
           startActivity(sendUserFormDataIntent)

       }
    }

}
