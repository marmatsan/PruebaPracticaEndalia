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
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.data.models.RegisteredUsers
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

fun validate(email: String, password: String, againPasswordState: String): Pair<String, String> {
    var userData: Pair<String, String> = Pair("", "")
    if (email.isNotEmpty() && password.isNotEmpty() && againPasswordState.isNotEmpty()) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password == againPasswordState) {
            userData = Pair(email, password)
        }
    }
    return userData
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
        CreateAccountButton(
            pair = validate(
                emailState.text,
                passwordState.text,
                againPasswordState.text
            )
        )
    }
}

@Composable
fun CreateAccountButton(pair: Pair<String, String>) {
    val currentContext = LocalContext.current

    Button(
        onClick = {
            if (!(pair.first == "" || pair.second == "")) {
                val registeredUsers = RegisteredUsers.loggedUsers

                val userEmailExists = registeredUsers.filter {
                    it.mail == pair.first
                }.size == 1

                val userPasswordExists = registeredUsers.filter {
                    it.password == pair.second
                }.size == 1

                if (!(userEmailExists || userPasswordExists)) { // User not created
                    Toast.makeText(currentContext, "Creando usuario", Toast.LENGTH_LONG).show()

                    currentContext.startActivity(
                        Intent(
                            currentContext,
                            DirectoryActivity::class.java
                        )
                    )

                } else { // User created
                    Toast.makeText(currentContext, "Usuario ya creado", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(currentContext, "Comprueba los campos", Toast.LENGTH_LONG).show()
            }

        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightEndalia)
    ) {
        Text(text = "CREAR CUENTA", color = Color.White)
    }
}