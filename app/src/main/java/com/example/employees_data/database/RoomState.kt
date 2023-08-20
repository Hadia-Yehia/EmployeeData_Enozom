package com.example.employees_data.database

import com.example.employees_data.model.EmployeeModel

sealed class RoomState{
    class SuccessSkills (val data: List<String>): RoomState()
    class SuccessEmployee(val data:List<EmployeeModel>):RoomState()
    class Failure(val msg:Throwable): RoomState()
    object Loading: RoomState()

}