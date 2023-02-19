package com.example.pruebapracticaandroid.ui

import android.webkit.WebSettings.TextSize
import androidx.annotation.ColorInt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.data.models.TextFieldState
import com.example.pruebapracticaandroid.ui.theme.LightEndalia
import kotlin.math.absoluteValue

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
fun EmailField(emailState : TextFieldState = remember { TextFieldState() }) {
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
            value = emailState.text,
            onValueChange = {
                emailState.text = it
            },
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
fun PasswordField(placeholder : String, passwordState : TextFieldState = remember { TextFieldState() }) {
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
            value = passwordState.text,
            onValueChange = {
                passwordState.text = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            visualTransformation = PasswordVisualTransformation()
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

@Composable
fun EmployeeImage(

    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
    fontSize : TextUnit = 20.sp
) {
    Box(modifier.size(size), contentAlignment = Alignment.Center) {
        val color = remember(firstName, lastName) {
            val name = listOf(firstName, lastName)
                .joinToString(separator = "")
                .uppercase()
            Color(name.toHslColor())
        }
        val initials = (firstName.take(1) + lastName.take(1)).uppercase()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = initials, style = textStyle, color = Color.White, fontSize = fontSize)
    }
}

@ColorInt
fun String.toHslColor(saturation: Float = 0.5f, lightness: Float = 0.4f): Int {
    val hue = fold(0) { acc, char -> char.code + acc * 37 } % 360
    return ColorUtils.HSLToColor(floatArrayOf(hue.absoluteValue.toFloat(), saturation, lightness))
}