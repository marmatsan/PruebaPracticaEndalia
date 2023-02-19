package com.example.pruebapracticaandroid.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.activities.DirectoryActivity

import com.example.pruebapracticaandroid.data.models.Employee
import com.example.pruebapracticaandroid.ui.theme.LightEndalia
import kotlin.math.absoluteValue

@Composable
fun EmployeeDetailScreen(employee: Employee) {
    Scaffold(topBar = {
        AppBar()
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            EmployeeDetails(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                employee = employee
            )
        }
    }
}

@Composable
fun AppBar() {
    val currentContext = LocalContext.current

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        currentContext.startActivity(
                            Intent(
                                currentContext,
                                DirectoryActivity::class.java
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Directory",
                        tint = Color.Black
                    )
                }
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun EmployeeDetails(modifier: Modifier, employee: Employee) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(8.dp))
        EmployeeImage(
            firstName = employee.name,
            lastName = employee.surname,
            size = 120.dp,
            fontSize = 60.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        EmployeeName(
            name = "${employee.name} ${employee.surname}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        EmployeeJob(job = employee.job, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(10.dp))
        ContactButtons(phone = employee.phone, mail = employee.mail)
        Spacer(modifier = Modifier.padding(10.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
        EmployeePhone(phone = employee.phone, modifier = Modifier.align(Alignment.Start))
        EmployeeMail(mail = employee.mail, modifier = Modifier.align(Alignment.Start))
    }
}

@Composable
fun EmployeeName(name: String, modifier: Modifier) {
    Text(text = name, modifier = modifier, fontWeight = FontWeight.Bold, fontSize = 26.sp)
}

@Composable
fun EmployeeJob(job: String, modifier: Modifier) {
    Text(text = job, modifier = modifier.alpha(ContentAlpha.medium), fontSize = 17.sp)
}

@Composable
fun ContactButtons(phone: Int, mail: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {
            val uri = Uri.parse("tel:$phone")
            val intent = Intent(Intent.ACTION_DIAL, uri)

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No se puede abrir la aplicación de llamadas",
                    Toast.LENGTH_LONG
                ).show()
            } catch (t: Throwable) {
                Toast.makeText(
                    context,
                    "Se produjo un error desconocido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.telephone),
                contentDescription = "Call employee",
                modifier = Modifier
                    .height(40.dp),
                tint = LightEndalia
            )
        }
        IconButton(onClick = {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "vnd.android.cursor.item/email"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No se puede abrir la aplicación de mail",
                    Toast.LENGTH_LONG
                ).show()
            } catch (t: Throwable) {
                Toast.makeText(
                    context,
                    "Se produjo un error desconocido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "Send email",
                modifier = Modifier
                    .height(40.dp),
                tint = LightEndalia
            )
        }
    }
}

@Composable
fun EmployeePhone(phone: Int, modifier: Modifier) {
    Text(
        text = "$phone",
        modifier = modifier
            .padding(top = 36.dp, start = 24.dp),
        fontSize = 16.sp,
    )
}


@Composable
fun EmployeeMail(mail: String, modifier: Modifier) {
    Text(
        text = mail,
        modifier = modifier
            .padding(top = 36.dp, start = 24.dp),
        fontSize = 16.sp
    )
}