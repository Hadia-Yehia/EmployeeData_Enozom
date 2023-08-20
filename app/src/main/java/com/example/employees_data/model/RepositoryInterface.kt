package com.example.employees_data.model


import com.example.employees_data.database.EmployeeSkillDao
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun insertEmployee(employee: EmployeeModel):Long
    fun getAllStoredEmployees(): Flow<List<EmployeeModel>>
   // suspend fun deleteEmployee(id:Int)
    suspend fun insertSkill(skill:SkillModel)
    fun getAllStoredSkills(): Flow<List<SkillModel>>
   // suspend fun deleteSkill(id:Int)
   suspend fun insertEmployeeSkill(skill:EmployeeSkills)
    fun getAllStoredEmployeeSkills(): Flow<List<EmployeeSkills>>
}