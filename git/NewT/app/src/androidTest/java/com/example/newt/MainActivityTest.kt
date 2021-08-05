package com.example.newt



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.testing.MainActivity
import com.example.testing.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    //Instance of valid Input
    var nameToPass = "Ayomide"
    var userNameToPass = "hayyohmyde"
    var emailToPass = "ayomide.ilori@gmail.com"
    var mobileToPass = "08034107426"
    var genderToPass = "male"
    var passwordToPass = "qwerty@86"
    var confirmedPasswordToPass = "qwerty@86"


    //Instance of invalid Input
    var invalidNameToPass = "Ayomide"
    var invalidUserNameToPass = "hay"
    var invalidEmailToPass = "ayomide.ilori@gmailcom"
    var invalidMobileToPass = "0807426"
    var invalidGenderToPass = "ale"
    var invalidPasswordToPass = "qwert86"
    var invalidConfirmedPasswordToPass = "qwert86"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    //Valid Input User Interface Test
   @Test
   fun registrationTest(){
       onView(withId(R.id.name)).perform(typeText(nameToPass), closeSoftKeyboard())
       onView(withId(R.id.userName)).perform(typeText(userNameToPass), closeSoftKeyboard())
       onView(withId(R.id.Email)).perform(typeText(emailToPass), closeSoftKeyboard())
       onView(withId(R.id.mobile)).perform(typeText(mobileToPass), closeSoftKeyboard())
       onView(withId(R.id.password)).perform(typeText(passwordToPass), closeSoftKeyboard())
       onView(withId(R.id.confirmedPassword)).perform(typeText(confirmedPasswordToPass), closeSoftKeyboard())
       onView(withId(R.id.register)).perform(click())
   }


    //Valid Input User Interface Test
    @Test
    fun invalidRegistrationTest(){
        onView(withId(R.id.name)).perform(typeText(invalidNameToPass), closeSoftKeyboard())
        onView(withId(R.id.userName)).perform(typeText(invalidUserNameToPass), closeSoftKeyboard())
        onView(withId(R.id.Email)).perform(typeText(invalidEmailToPass), closeSoftKeyboard())
        onView(withId(R.id.mobile)).perform(typeText(invalidMobileToPass), closeSoftKeyboard())
        onView(withId(R.id.textGender)).check(matches(withText(genderToPass)))
        onView(withId(R.id.password)).perform(typeText(invalidPasswordToPass), closeSoftKeyboard())
        onView(withId(R.id.confirmedPassword)).perform(typeText(invalidConfirmedPasswordToPass), closeSoftKeyboard())
        onView(withId(R.id.register)).perform(click())
    }

    //Testing User's displayed Input
//    @Test
//    fun displayedInputTest(){
//        onView(withId(R.id.textName)).check(matches(withText(nameToPass)))
//        onView(withId(R.id.textUserName)).check(matches(withText(userNameToPass)))
//        onView(withId(R.id.textMobile)).check(matches(withText(mobileToPass)))
//        onView(withId(R.id.textGender)).check(matches(withText(genderToPass)))
//        onView(withId(R.id.register)).perform(click())
//    }


}