package com.tupleinfotech.jetpackloginui

sealed class Routes(val route : String){

    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Dashboard : Routes("Dashboard")
    object Home : Routes("Home")
}
