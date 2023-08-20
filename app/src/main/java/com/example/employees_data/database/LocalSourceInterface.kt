package com.example.employees_data.database

import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.flow.Flow

interface LocalSourceInterface {
    suspend fun insertEmployee(employee:EmployeeModel):Long
    fun getAllStoredEmployees(): Flow<List<EmployeeModel>>
    //suspend fun deleteEmployee(id:Int)

    suspend fun insertSkill(skill:SkillModel)
    fun getAllStoredSkills(): Flow<List<SkillModel>>
    //suspend fun deleteSkill(id:Int)

    suspend fun insertEmployeeSkill(skill:EmployeeSkills)
    fun getAllStoredSkillsForEmployee(): Flow<List<EmployeeSkills>>
}