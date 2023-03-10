package com.example.pruebapracticaandroid.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.example.pruebapracticaandroid.data.models.Employee
import com.example.pruebapracticaandroid.ui.EmployeeDetailScreen
import com.example.pruebapracticaandroid.ui.theme.PruebaPracticaAndroidTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson

class EmployeeDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val employeeAsGson = intent.getStringExtra("employeeAsGson")
        val employeeData = Gson().fromJson(employeeAsGson, Employee::class.java)

        setContent {
            PruebaPracticaAndroidTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = Color.White)

                EmployeeDetailScreen(employeeData)
            }
        }
    }
}