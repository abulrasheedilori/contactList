package com.example.newt

import org.junit.Assert.*
import org.junit.Test

class ValidationTest {

    //creating an object


    //testing email validation
    @Test
    fun validate_email() {
        assertEquals(true, Validation.email_validation("okezi002@gmail.com"))
    }

    //testing username validation
    @Test
    fun validate_userName() {
        assertEquals(true, Validation.userName_validation("hayyohmyde"))
    }

    //testing name validation
    @Test
    fun validate_name() {
        assertEquals(true, Validation.name_validation("Okezie"))
    }

    //testing mobile validation
    @Test
    fun validate_mobile() {
        assertEquals(true, Validation.mobile_validation("08034651008"))
    }

    //testing gender validation
    @Test
    fun validate_gender() {
        assertEquals(true, Validation.gender_validation("male"))
    }

    //testing password validation
    @Test
    fun validate_password() {
        assertEquals(true, Validation.password_validation("$#OOkezi4life$"))
    }

    //testing confirmedPassword validation
    @Test
    fun validate_confirmedPassword() {
        assertEquals(true, Validation.confirmedPassword_validation("qwerty@86","qwerty@86"))
    }
}