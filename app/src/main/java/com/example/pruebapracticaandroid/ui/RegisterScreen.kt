package com.example.pruebapracticaandroid.ui

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
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
import com.example.pruebapracticaandroid.activities.LoginActivity
import com.example.pruebapracticaandroid.data.models.*
import com.example.pruebapracticaandroid.data.models.DirectoryData
import com.example.pruebapracticaandroid.ui.theme.LightEndalia
import java.lang.Character.*
import java.util.regex.Pattern
import kotlin.random.Random


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

fun validate(email: String, password: String, repeatedPassword: String): TextInputData {
    val inputData = TextInputData(
        email = email,
        password = password,
        error = TextInputError.NO_ERROR
    )

    if (email.isEmpty()) {
        inputData.error = TextInputError.EMAIL_EMPTY
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && !email.matches("^[a-z]+[.][a-z]+@demo[.]com\$".toRegex())
    ) {
        inputData.error = TextInputError.INVALID_EMAIL_FORMAT
    } else if (password.isEmpty()) {
        inputData.error = TextInputError.PASSWORD_EMPTY
    } else if (repeatedPassword.isEmpty()) {
        inputData.error = TextInputError.REPEATED_PASSWORD_EMPTY
    } else if (password.isNotEmpty() && password.length < 8) {
        inputData.error = TextInputError.SHORT_PASSWORD
    } else if (password != repeatedPassword) {
        inputData.error = TextInputError.NOT_MATCHING_PASSWORD
    } else {
        val hasUpperCase: Boolean = password.any(::isUpperCase)
        val hasLowerCase: Boolean = password.any(::isLowerCase)
        val hasDigit: Boolean = password.any(::isDigit)
        val hasPunctuation: Boolean = password.any { Pattern.matches("\\p{Punct}", it.toString()) }

        if (!(hasUpperCase && hasLowerCase && hasDigit && hasPunctuation)) {
            inputData.error = TextInputError.INVALID_PASSWORD_FORMAT
        }
    }

    return inputData
}

@Composable
fun Register(modifier: Modifier) {
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }
    val againPasswordState = remember { TextFieldState() }

    Column(modifier = modifier) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(emailState = emailState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(placeholder = "Contraseña", passwordState = passwordState)
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(
            placeholder = "Vuelve a introducir contraseña", passwordState = againPasswordState
        )
        Spacer(modifier = Modifier.padding(24.dp))
        CreateAccountButton(
            inputData = validate(
                email = emailState.text,
                password = passwordState.text,
                repeatedPassword = againPasswordState.text
            )
        )
    }
}

@Composable
fun CreateAccountButton(inputData: TextInputData) {
    val currentContext = LocalContext.current

    Button(
        onClick = {
            val error = inputData.error

            if (error == TextInputError.NO_ERROR) { // No input error
                val registeredUsers = RegisteredUsers.loggedUsers

                val userEmailExists = registeredUsers.filter {
                    it.mail == inputData.email
                }.size == 1

                if (!userEmailExists) { // User not created. Register user
                    Toast.makeText(currentContext, "Creando usuario", Toast.LENGTH_LONG).show()

                    val nameAndFirstName = inputData.email.substringBefore("@")
                    val name = nameAndFirstName.substringBefore(".").replaceFirstChar{
                        it.uppercaseChar()
                    }
                    val fistName = nameAndFirstName.substringAfter(".").replaceFirstChar {
                        it.uppercaseChar()
                    }

                    val newEmployee = Employee(
                        name = name,
                        surname = fistName,
                        job = DirectoryData.jobs[Random.nextInt(7)],
                        phone = Random.nextInt(900000000, 1000000000),
                        mail = inputData.email,
                        password = inputData.password
                    )

                    registeredUsers.add(newEmployee)
                    DirectoryData.listOfEmployees.add(newEmployee)

                    currentContext.startActivity(Intent(currentContext, LoginActivity::class.java))
                } else { // User created
                    Toast.makeText(currentContext, "Usuario ya creado", Toast.LENGTH_LONG).show()
                }

            } else { // Input error
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
                    TextInputError.REPEATED_PASSWORD_EMPTY -> Toast.makeText(
                        currentContext,
                        "Vuelve a introducir la contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                    TextInputError.SHORT_PASSWORD -> Toast.makeText(
                        currentContext,
                        "La contraseña debe tener al menos 8 caracteres",
                        Toast.LENGTH_SHORT
                    ).show()
                    TextInputError.INVALID_EMAIL_FORMAT -> Toast.makeText(
                        currentContext,
                        "Formato no válido. El formato debe ser nombre.apellido@demo.com",
                        Toast.LENGTH_SHORT
                    ).show()
                    TextInputError.INVALID_PASSWORD_FORMAT -> Toast.makeText(
                        currentContext,
                        "La contraseña debe tener al menos una minúscula, mayúscula, un número y un caracter de puntuación",
                        Toast.LENGTH_LONG
                    ).show()
                    TextInputError.NOT_MATCHING_PASSWORD -> Toast.makeText(
                        currentContext,
                        "Las contraseñas no coinciden",
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

        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightEndalia)
    ) {
        Text(text = "CREAR CUENTA", color = Color.White)
    }
}