package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pruebapracticaandroid.ui.DirectoryScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme

class DirectoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                DirectoryScreen()
            }
        }
    }
}