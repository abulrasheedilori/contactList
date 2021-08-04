package com.example.newt

import org.junit.Assert.*
import org.junit.Test

class ValidationTest {

    //creating an object
    val Sample = Validation()

    //testing email validation
    @Test
    fun validate_email() {
        assertEquals("Successful", Sample.email_validation("okezi002@gmail.com"))
    }

    
    @Test
    fun validate_userName() {
        assertEquals("Successful", Sample.userName_validation("okezi002@gmail.com"))
    }

    @Test

}