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

    var nameToPass = "Ayomide"
    var userNameToPass = "hayyohmyde"
    var emailToPass = "ayomide.ilori@gmail.com"
    var mobileToPass = "08034107426"
    var passwordToPass = "qwerty@86"
    var confirmedPasswordToPass = "qwerty@86"


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

   @Test
   fun registrationTest(){
       onView(withId(R.id.name))
           .perform(typeText(nameToPass), closeSoftKeyboard())

       //usercheck
       onView(withId(R.id.userName))
           .perform(typeText(userNameToPass), closeSoftKeyboard())

       //email
//       onView(withId(R.id.Email))
//           .perform(typeText(emailToPass), closeSoftKeyboard())

       //testing mobile
       onView(withId(R.id.mobile))
           .perform(typeText(mobileToPass), closeSoftKeyboard())

       //testing password
       onView(withId(R.id.password))
           .perform(typeText(confirmedPasswordToPass), closeSoftKeyboard())

       //Register button Clicked

        onView(withId(R.id.register)).perform(click())
   }

}