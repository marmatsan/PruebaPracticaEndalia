package com.example.pruebapracticaandroid.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.LoginActivity
import com.example.pruebapracticaandroid.data.models.Employee
import com.example.pruebapracticaandroid.data.models.HomeViewModel
import com.example.pruebapracticaandroid.data.models.TextFieldState
import com.example.pruebapracticaandroid.ui.theme.LightEndalia

@Composable
fun RegisterScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Register(modifier = Modifier.align(Alignment.Center))
        BottomImage(modifier = Modifier.align(Alignment.BottomStart))
    }
}

fun validate(email: String, password: String, againPasswordState: String): Boolean {
    var validFields = false
    if (email.isNotEmpty() && password.isNotEmpty() && againPasswordState.isNotEmpty()) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password == againPasswordState) {
            validFields = true
        }
    }
    return validFields
}

@Composable
fun Register(modifier: Modifier) {
    var emailState = remember { TextFieldState() }
    var passwordState = remember { TextFieldState() }
    var againPasswordState = remember { TextFieldState() }

    Column(modifier = modifier) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(emailState = emailState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(placeholder = "Contraseña", passwordState = passwordState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(
            placeholder = "Vuelve a introducir contraseña",
            passwordState = againPasswordState
        )
        Spacer(modifier = Modifier.padding(24.dp))
        CreateAccountButton(valid = validate(emailState.text, passwordState.text, againPasswordState.text))
    }
}

@Composable
fun CreateAccountButton(valid: Boolean) {
    val currentContext = LocalContext.current

    Button(
        onClick = {
            if (valid) {
                Log.d("DEBUG", "Creando cuenta")
                currentContext.startActivity(Intent(currentContext, LoginActivity::class.java))
            } else {
                Log.d("DEBUG", "Comprueba los campos")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightEndalia)
    ) {
        Text(text = "CREAR CUENTA", color = Color.White)
    }
}

fun addEmployeeInDB(
    employee: Employee,
    homeViewModel: HomeViewModel
) {
    homeViewModel.addEmployee(employee)
}