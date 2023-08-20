package com.example.employees_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeSkillDao {
    @Query("SELECT * FROM employee_skills_table")
    fun getAllEmployeeSkills(): Flow<List<EmployeeSkills>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployeeSkill(employeeSkill: EmployeeSkills)
}