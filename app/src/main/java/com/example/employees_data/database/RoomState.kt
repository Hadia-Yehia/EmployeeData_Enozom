package com.example.employees_data.database

import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import com.example.employees_data.model.SkillModel

sealed class RoomState{
    class SuccessSkills (val data: List<SkillModel>): RoomState()
    class SuccessEmployee(val data:List<EmployeeModel>):RoomState()
    class SuccessEmployeeSkills(val data:List<EmployeeSkills>):RoomState()
    class SuccessEmployeeInsertion(val data:Long):RoomState()
    class Failure(val msg:Throwable): RoomState()
    object Loading: RoomState()

}