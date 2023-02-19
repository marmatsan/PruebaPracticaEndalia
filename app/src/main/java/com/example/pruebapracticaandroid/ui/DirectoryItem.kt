package com.example.pruebapracticaandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebapracticaandroid.data.models.Employee

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
        EmployeeImage(
            firstName = employee.name,
            lastName = employee.surname
        )
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Text(text = "${employee.surname}, ${employee.name}", fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = employee.job, color = Color.Gray)
        }
    }
}

