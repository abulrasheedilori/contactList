package com.example.newt


class Validation {
        //email validation
    fun email_validation( email: String) : String{
        var response = "Successful"
        if(email.isEmpty() || !email.contains("@") || !email.contains('.')){
            response = "Input a valid email"
        }
        return response
    }

    //Username validation
    fun userName_validation(userName: String) : String{
        var response = "Successful"
        if(userName.length < 4 || userName.isEmpty()){
            response = "Username should be more than four characters"
        }

        return response
    }

    //name validation
    fun name_validation(name: String) : String{
        var response = "Successful"
        if(name.length < 1 || name.isEmpty()){
            response = "Name should not be empty"
        }

        return response
    }

    //mobile number validation
    fun mobile_validation(mobile: String) : String{
        var response = "Successful"
        if(mobile.length < 6 || mobile.isEmpty()){
            response = "Your mobile number should not be empty"
        }

        return response
    }

    //gender validation
    fun gender_validation(gender: String) : String{
        var response = "Successful"
        if(gender.length < 1 || gender.isEmpty()){
            response = "Please, select your gender"
        }

        return response
    }




}

