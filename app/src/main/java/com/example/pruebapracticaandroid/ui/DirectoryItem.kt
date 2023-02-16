package com.example.pruebapracticaandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.model.Employee

@Composable
fun DirectoryItem(employee: Employee) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.endalia_isotipo_512),
            contentDescription = "Image",
            modifier = Modifier.height(50.dp)
        )
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()) {
            Text(text = "${employee.surname}, ${employee.name}")
            Text(text = employee.job)
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DirectoryItemPreview() {
    DirectoryItem(
        employee = Employee(
            id = 0,
            name = "Miguel",
            surname = "Abad Sánchez",
            job = "Técnico de RRHH",
            phone = 987654321,
            mail = "miguel.abad@demo.com",
            imageId = 0
        )
    )
}