package com.example.pruebapracticaandroid.data.models

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees = MutableLiveData<List<Employee>>()
    val foundEmployee = MutableLiveData<Employee>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addEmployee(newEmployee: Employee) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.addEmployee(newEmployee)
        }
    }

    fun getAllEmployees() {
        coroutineScope.launch(Dispatchers.IO) {
            allEmployees.postValue(employeeDao.getAllEmployees())
        }
    }

}