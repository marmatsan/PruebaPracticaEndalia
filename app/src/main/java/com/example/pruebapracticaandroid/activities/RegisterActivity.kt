package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pruebapracticaandroid.ui.RegisterScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                RegisterScreen()
            }
        }
    }
}