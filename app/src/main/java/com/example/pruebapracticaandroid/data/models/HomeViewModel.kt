package com.example.pruebapracticaandroid.data.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) :
    ViewModel() {

    val employeeList: LiveData<List<Employee>> = employeeRepository.allEmployees

    val foundEmployee: LiveData<Employee> = employeeRepository.foundEmployee

    fun getAllEmployees() {
        employeeRepository.getAllEmployees()
    }

    fun addEmployee(employee: Employee) {
        employeeRepository.addEmployee(employee)
        getAllEmployees()
    }
}