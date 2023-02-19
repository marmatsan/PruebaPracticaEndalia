package com.example.pruebapracticaandroid.ui

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.LoginActivity
import com.example.pruebapracticaandroid.activities.RegisterActivity
import com.example.pruebapracticaandroid.data.models.DirectoryData
import com.example.pruebapracticaandroid.data.models.TextFieldState
import com.example.pruebapracticaandroid.data.models.TextInputData
import com.example.pruebapracticaandroid.data.models.TextInputError
import com.example.pruebapracticaandroid.ui.theme.LightEndalia

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(18.dp)
    ) {
        Login(modifier = Modifier.align(Alignment.Center))
        BottomImage(modifier = Modifier.align(Alignment.BottomStart))
    }
}

fun validate(email: String, password: String): TextInputData {
    var inputData = TextInputData(
        email = email,
        password = password,
        error = TextInputError.NO_ERROR
    )

    if (email.isEmpty()) {
        inputData.error = TextInputError.EMAIL_EMPTY
    } else if (password.isEmpty()) {
        inputData.error = TextInputError.PASSWORD_EMPTY
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        inputData.error = TextInputError.INVALID_EMAIL_FORMAT
    }

    return inputData
}

@Composable
fun Login(modifier: Modifier) {
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(emailState = emailState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(placeholder = "Contraseña", passwordState = passwordState)
        Spacer(modifier = Modifier.padding(24.dp))
        LoginButton(inputData = validate(email = emailState.text, password = passwordState.text))
        RegisterButton()
    }
}

@Composable
fun LoginButton(inputData: TextInputData) {
    val currentContext = LocalContext.current
    Button(
        onClick = {
            currentContext.startActivity(
                Intent(
                    currentContext,
                    DirectoryActivity::class.java
                )
            )
            /*
            val error = inputData.error

            if (error == TextInputError.NO_ERROR) {
                val directoryData = DirectoryData.listOfEmployees

                try {
                    val userFound = directoryData.first {
                        it.mail == inputData.email
                    }

                    if (userFound.password == inputData.password) {
                        currentContext.startActivity(
                            Intent(
                                currentContext,
                                DirectoryActivity::class.java
                            )
                        )
                    } else {
                        Toast.makeText(
                            currentContext,
                            "Contraseña incorrecta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: NoSuchElementException) {
                    Toast.makeText(
                        currentContext,
                        "Usuario no registrado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                when (error) {
                    TextInputError.EMAIL_EMPTY -> Toast.makeText(
                        currentContext,
                        "Introduce un correo",
                        Toast.LENGTH_SHORT
                    ).show()
                    TextInputError.PASSWORD_EMPTY -> Toast.makeText(
                        currentContext,
                        "Introduce una contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                    TextInputError.INVALID_EMAIL_FORMAT -> Toast.makeText(
                        currentContext,
                        "El correo introducido no es válido",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        Toast.makeText(
                            currentContext,
                            "Error desconocido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            */
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