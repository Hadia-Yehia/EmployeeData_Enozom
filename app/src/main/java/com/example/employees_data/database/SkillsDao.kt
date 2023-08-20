package com.example.employees_data.database

import androidx.room.*
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.flow.Flow
@Dao
interface SkillsDao {
        @Query("SELECT * FROM skills_table")
        fun getAllSkills(): Flow<List<SkillModel>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertSkill(skill: SkillModel)

//        @Delete
//        suspend fun deleteSkill(id:Int)




}