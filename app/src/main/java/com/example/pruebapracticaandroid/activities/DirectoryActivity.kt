package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import com.example.pruebapracticaandroid.data.models.MainViewModel
import com.example.pruebapracticaandroid.ui.DirectoryScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class DirectoryActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White)

                DirectoryScreen(mainViewModel)
            }
        }
    }
}