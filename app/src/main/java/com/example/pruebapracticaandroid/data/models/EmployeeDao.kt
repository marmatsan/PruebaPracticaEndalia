package com.example.pruebapracticaandroid.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE id = :empId")
    fun findEmployeeById(empId: String): Employee

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>
}