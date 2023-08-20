package com.example.employees_data.database

import androidx.room.*
import com.example.employees_data.model.EmployeeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees_table")
    fun getAllEmployees(): Flow<List<EmployeeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeModel):Long

//    @Delete
//    suspend fun deleteEmployee(id:Int)
}