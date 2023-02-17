package com.example.pruebapracticaandroid.ui

import android.content.Intent
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.RegisterActivity
import com.example.pruebapracticaandroid.data.models.TextFieldState
import com.example.pruebapracticaandroid.ui.theme.LightEndalia

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Login(modifier = Modifier.align(Alignment.Center))
        BottomImage(modifier = Modifier.align(Alignment.BottomStart))
    }
}

fun validate(email: String, password: String): Boolean {
    var validEmail = false
    if (email.isNotEmpty() && password.isNotEmpty()) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            validEmail = true
        }
    }
    return validEmail
}

@Composable
fun Login(modifier: Modifier) {
    var emailState = remember { TextFieldState() }
    var passwordState = remember { TextFieldState() }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(emailState = emailState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(placeholder = "Contraseña", passwordState = passwordState)
        Spacer(modifier = Modifier.padding(24.dp))
        LoginButton(valid = validate(email = emailState.text, password = passwordState.text))
        RegisterButton()
    }
}

@Composable
fun LoginButton(valid: Boolean) {
    val currentContext = LocalContext.current
    Button(
        onClick = {
            if (valid) {
                currentContext.startActivity(Intent(currentContext, DirectoryActivity::class.java))
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(0.5.dp, Color.Gray),
    ) {
        Text(text = "ACCEDER", color = Color.Gray)
    }
}

@Composable
fun RegisterButton() {
    val currentContext = LocalContext.current
    Button(
        onClick = {
            currentContext.startActivity(Intent(currentContext, RegisterActivity::class.java))
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightEndalia)
    ) {
        Text(text = "REGISTRARME", color = Color.White)
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}