package com.example.pruebapracticaandroid.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.RegisterActivity

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
    Column(modifier = modifier) {
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
    Button(onClick = {
        currentContext.startActivity(Intent(currentContext, DirectoryActivity::class.java))
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "ACCEDER")
    }
}

@Composable
fun RegisterButton() {
    val currentContext = LocalContext.current

    Button(onClick = {
        currentContext.startActivity(Intent(currentContext, RegisterActivity::class.java))
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "REGISTRARME")
    }
}