package com.example.pruebapracticaandroid.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.activities.DirectoryActivity

import com.example.pruebapracticaandroid.model.Employee

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
        EmployeePicture(
            imageId = employee.imageId,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        EmployeeName(
            name = "${employee.name} ${employee.surname} ",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(2.dp))
        EmployeeJob(job = employee.job, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
        ContactButtons()
        Spacer(modifier = Modifier.padding(6.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
        EmployeePhone(phone = employee.phone, modifier = Modifier.align(Alignment.Start))
        EmployeeMail(mail = employee.mail, modifier = Modifier.align(Alignment.Start))
    }
}

@Composable
fun EmployeePicture(imageId: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.endalia_isotipo_512),
        contentDescription = "Profile picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(78.dp)
            .clip(CircleShape)
    )
}

@Composable
fun EmployeeName(name: String, modifier: Modifier) {
    Text(text = name, modifier = modifier, fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

@Composable
fun EmployeeJob(job: String, modifier: Modifier) {
    Text(text = job, modifier = modifier.alpha(ContentAlpha.medium), fontSize = 12.sp)
}

@Composable
fun ContactButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(id = R.drawable.telephone),
            contentDescription = "Call employee",
            modifier = Modifier
                .height(40.dp)
                .padding(top = 12.dp, end = 10.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.email),
            contentDescription = "Send email",
            modifier = Modifier
                .height(40.dp)
                .padding(top = 12.dp, end = 10.dp)
        )
    }
}

@Composable
fun EmployeePhone(phone: Int, modifier: Modifier) {
    Text(text = "$phone", modifier = modifier.alpha(ContentAlpha.medium).padding(top = 24.dp, start = 18.dp), fontSize = 14.sp)
}


@Composable
fun EmployeeMail(mail: String, modifier: Modifier) {
    Text(text = mail, modifier = modifier.alpha(ContentAlpha.medium).padding(top = 24.dp, start = 18.dp), fontSize = 14.sp)
}

@Preview
@Composable
fun PreviewEmployeeDetailScreen() {
    EmployeeDetailScreen(
        Employee(
            id = 0,
            name = "Antonio",
            surname = "García Gutierrez",
            job = "Técnico de RRHH",
            phone = 987654321,
            mail = "antonio.garcia@demo.com",
            imageId = 0
        )
    )
}