package com.example.pruebapracticaandroid.data.models

data class Employee(
    val id: Int,
    val name: String,
    val surname: String,
    val job: String,
    val phone: Int,
    val mail: String,
    val imageId: Int = 0
)