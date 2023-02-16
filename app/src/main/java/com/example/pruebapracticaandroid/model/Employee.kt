package com.example.pruebapracticaandroid.model

data class Employee(
    val id: Int,
    val name: String,
    val surname: String,
    val job: String,
    val phone: Int,
    val mail: String,
    val imageId: Int = 0
)