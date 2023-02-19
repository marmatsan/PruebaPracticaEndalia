package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.example.pruebapracticaandroid.ui.RegisterScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White)

                RegisterScreen()
            }
        }
    }
}