package com.example.pruebapracticaandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.ui.theme.LightEndalia

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.endalia_logo_blue),
        contentDescription = "Endalia Logo",
        modifier = modifier
            .width(320.dp)
            .height(120.dp)

    )
}

@Composable
fun EmailField() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.user_logo),
            contentDescription = "User Logo",
            modifier = Modifier
                .height(40.dp)
                .padding(top = 12.dp, end = 10.dp),
            tint = LightEndalia
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Usuario"
                )
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
    }
}

@Composable
fun PasswordField() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.password_logo),
            contentDescription = "Password Logo",
            modifier = Modifier
                .height(40.dp)
                .padding(top = 12.dp, end = 10.dp),
            tint = LightEndalia
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Contraseña"
                )
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )
    }
}


@Composable
fun BottomImage(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Powered by", color = Color.Gray)
        Spacer(modifier = Modifier.padding(4.dp))
        Image(
            painter = painterResource(id = R.drawable.software_logo_endalia_mobile),
            contentDescription = "Bottom Image"
        )
    }

}