package com.example.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.marginBottom
import com.example.newt.UserDataInfo
import com.example.newt.Validation

import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var gender = arrayOf<String?>("male", "female")
    var genderChoice = ""


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
            val confirmedPassword =
                findViewById<EditText>(R.id.confirmedPassword).text.toString().trim()

                    // calling edittext label
            val label_name = findViewById<TextInputLayout>(R.id.NameLabel)
            val label_userName = findViewById<TextInputLayout>(R.id.userNameLabel)
            val label_email = findViewById<TextInputLayout>(R.id.emailLabel)
            val label_mobile = findViewById<TextInputLayout>(R.id.mobileLabel)
            val label_password = findViewById<TextInputLayout>(R.id.passwordLabel)
            val label_confirmedPassword = findViewById<TextInputLayout>(R.id.confirmedPasswordLabel)



            val validate_name = Validation.name_validation(name)
            val validate_username = Validation.userName_validation(userName)
            val validate_email = Validation.email_validation(email)
            val validate_mobile = Validation.mobile_validation(mobile)
            val validate_password = Validation.password_validation(password)
            val validate_confirmedPassword = Validation.confirmedPassword_validation(password, confirmedPassword)

            if(validate_name && validate_username && validate_email && validate_mobile
                && validate_password && validate_confirmedPassword){
                //sending user message to display view
                val sendUserFormDataIntent = Intent(this, UserDataInfo::class.java)

                sendUserFormDataIntent.putExtra("name", name)
                sendUserFormDataIntent.putExtra("username", userName)
                sendUserFormDataIntent.putExtra("email", email)
                sendUserFormDataIntent.putExtra("mobile", mobile)
                sendUserFormDataIntent.putExtra("password", password)
                sendUserFormDataIntent.putExtra("confirmedPassword", confirmedPassword)
                 sendUserFormDataIntent.putExtra("gender", genderChoice)
                startActivity(sendUserFormDataIntent)
            }else{
                    //Validating error status
                if(!validate_name){
                    //label_name.marginBottom
                    label_name.error = "Name should be more than 4 characters"
                }else{
                    label_name.error = null
                }
                if(!validate_username){
                    label_userName.error = "invalid username "
                }else{
                    label_userName.error = null
                }
                if(!validate_email){
                    label_email.error = "invalid Email"
                }else{
                    label_email.error = null
                }
                if(!validate_mobile){
                    label_mobile.error = "invalid Mobile"
                }else{
                    label_mobile.error = null
                }
                if(!validate_password){
                    label_password.error = "invalid Password"
                }else{
                    label_password.error = null
                }
                if(!validate_confirmedPassword){
                    label_confirmedPassword.error = "Password donot match"
                }else{
                    label_confirmedPassword.error = null
                }
            }
        }
    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        genderChoice = gender[p2]!!
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}
