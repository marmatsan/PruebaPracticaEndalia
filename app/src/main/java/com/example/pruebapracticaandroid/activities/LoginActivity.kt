package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.pruebapracticaandroid.ui.LoginScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White)

                LoginScreen()
            }
        }
    }
}