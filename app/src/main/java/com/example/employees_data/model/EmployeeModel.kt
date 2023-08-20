package com.example.employees_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees_table")
data class EmployeeModel(var name:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var photo :String? = null
    var Email :String? = null
    var Skills:ArrayList<String>? = null

}