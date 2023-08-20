package com.example.employees_data.model


import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun insertEmployee(employee: EmployeeModel)
    fun getAllStoredEmployees(): Flow<List<EmployeeModel>>
   // suspend fun deleteEmployee(id:Int)
    suspend fun insertSkill(skill:SkillModel)
    fun getAllStoredSkills(): Flow<List<SkillModel>>
   // suspend fun deleteSkill(id:Int)
}