package com.example.employees_data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "employee_skills_table", primaryKeys = ["employeeId","skill"],foreignKeys=[ForeignKey(entity = EmployeeModel::class, parentColumns = arrayOf("id"), childColumns = arrayOf("employeeId"), onDelete = ForeignKey.CASCADE)]
)
data class EmployeeSkills(var employeeId : Long,var skill:String) {

}