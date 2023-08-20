package com.example.employees_data.model

data class EmployeeModel(var name:String) {
    var photo :String? = null
    var Email :String? = null
    var Skills:ArrayList<String>? = null
}