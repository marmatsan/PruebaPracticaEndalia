package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.pruebapracticaandroid.model.MainViewModel
import com.example.pruebapracticaandroid.ui.DirectoryScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme

class DirectoryActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaPracticaAndroidTheme {
                DirectoryScreen(mainViewModel)
            }
        }
    }
}