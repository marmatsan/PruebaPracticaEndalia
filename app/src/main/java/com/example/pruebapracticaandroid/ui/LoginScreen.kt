package com.example.pruebapracticaandroid.ui

import android.content.Intent
import android.hardware.lights.Light
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.RegisterActivity
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

@Composable
fun Login(modifier: Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField()
        Spacer(modifier = Modifier.padding(24.dp))
        LoginButton()
        RegisterButton()
    }
}

@Composable
fun LoginButton() {
    val currentContext = LocalContext.current
    Button(
        onClick = {
            currentContext.startActivity(Intent(currentContext, DirectoryActivity::class.java))
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