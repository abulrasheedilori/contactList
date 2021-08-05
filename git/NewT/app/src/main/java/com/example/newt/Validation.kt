package com.example.newt


object Validation {


        //email validation
    fun email_validation( email: String) : Boolean{
        var response = true
        if(email.isEmpty() || !email.contains("@") || !email.contains('.')){
            response = false
        }
        return response
    }

    //Username validation
    fun userName_validation(userName: String) : Boolean{
        var response = true
        if(userName.length < 4 || userName.isEmpty()){
            response = false
        }

        return response
    }

    //name validation
    fun name_validation(name: String) : Boolean{
        var response = true
        if(name.length < 1 || name.isEmpty()){
            response = false
        }

        return response
    }

    //mobile number validation
    fun mobile_validation(mobile: String) : Boolean{
        var response = true
        if(mobile.length < 6 || mobile.isEmpty()){
            response = false
        }

        return response
    }

    //gender validation
    fun gender_validation(gender: String) : Boolean{
        var response =true
        if(gender.length < 1 || gender.isEmpty()){
            response =false
        }

        return response
    }

    //password validation
    fun password_validation(password: String) : Boolean{
        var response = true
        if(password.length < 8 || password.isEmpty()){
            response = false
        }

        return response
    }

    //confirmed Password validation
    fun confirmedPassword_validation(password: String, confirmedPassword:String) : Boolean{
        var response = true
        if(password != confirmedPassword){
            response = false
        }

        return response
    }


//    fun generalValidaion(email: String, userName: String,mobile: String,name: String,password: String,confirmPassword: String) : String{
//        var response  = "Sucessful"
//
//            response    =   confirmedPassword_validation(password, confirmPassword)
//            response    =    password_validation(password)
//            response    = mobile_validation(mobile)
//            response   = email_validation(email)
//            response =    userName_validation(userName)
//            response =   name_validation(name)
//
//       return response
//
//    }



}

