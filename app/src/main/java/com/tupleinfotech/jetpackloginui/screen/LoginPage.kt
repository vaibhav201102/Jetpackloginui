package com.tupleinfotech.jetpackloginui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tupleinfotech.jetpackloginui.Routes
import com.tupleinfotech.jetpackloginui.ui.theme.Purple80
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf

@ExperimentalMaterial3Api
@Composable
fun LoginPage(navController: NavController) {

    Box (modifier = Modifier.fillMaxSize()){
        ClickableText(text = AnnotatedString("Sign up here"),
            onClick = { navController.navigate(Routes.SignUp.route) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Cursive,
                textDecoration = TextDecoration.Underline,
                color = Color.Black
            )
        )
    }
    Column (
        modifier = Modifier.height(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val username = remember {
            mutableStateOf(TextFieldValue())
        }
        val password = remember {
            mutableStateOf(TextFieldValue())
        }
        var usernameError = remember {
            mutableStateOf<String?>(null)
        }
        var passwordError = remember {
            mutableStateOf<String?>(null)
        }
        val focusRequesterPassword = remember { FocusRequester() }


        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Username")},
            value = username.value,
            onValueChange = {username.value = it},
            isError = usernameError.value != null,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusRequesterPassword.requestFocus()})
        )

        if (usernameError.value != null) {
            usernameError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
//            onValueChange = onPasswordChange,
            onValueChange = { password.value = it },
            isError = passwordError.value != null,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),

            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
            ),

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),

            keyboardActions = KeyboardActions(onDone = { })
        )
        if (passwordError.value != null) {
            passwordError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    onLoginClicked(
                        username.value.text,
                        password.value.text,
                        usernameError,
                        passwordError,
                        navController
                    )
                },

                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { navController.navigate(Routes.ForgotPassword.route) },
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
        )


    }

}
fun onLoginClicked(username: String, password: String, usernameError: MutableState<String?>,
                   passwordError: MutableState<String?>,navController: NavController) {
    val isValidUsername = username.isNotEmpty()
    val isValidPassword = password.isNotEmpty()

    if (username.isEmpty()) {
        usernameError.value = "Please enter a username"
    } else {
        usernameError.value = null
    }

    if (password.isEmpty()) {
        passwordError.value = "Please enter a password"
    } else {
        passwordError.value = null
    }

    if (username.isNotEmpty() && password.isNotEmpty()) {
        // Perform the login action here
        navController.navigate(Routes.Home.route)
        println("Logging in with username: $username, password: $password")
    }

    // Additional validations can be added here if needed
    // For example, you can check if the username and password meet certain criteria

    /*if (username.isEmpty() == true) {
        println("1")
        usernameError.value = "Please enter a username"
    }
*//*    else if(username.isEmpty() == false){
        println("2")
        usernameError.value = null
    }*//*
    else if(password.isEmpty()== true){
        println("3")
        passwordError.value = "Please enter a password"
    }
*//*    else if (password.isNotEmpty()== true){
        println("4")
        passwordError.value = null
    }*//*
    else if(username.isEmpty() && password.isEmpty()){
        println("5")
        usernameError.value = "Please enter a username"
        passwordError.value = "Please enter a password"
    }
    else if(username.isNotEmpty() && password.isNotEmpty()){
        println("6")
    }*/
}