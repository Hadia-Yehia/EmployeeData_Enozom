package com.example.employees_data.database

import android.content.Context
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.flow.Flow

class LocalSource(context:Context):LocalSourceInterface {
    private val skillsDao:SkillsDao by lazy {
        val db:DataBase=DataBase.getInstance(context)
        db.getSkillsDao()
    }
    private val employeeDao:EmployeeDao by lazy {
        val db:DataBase=DataBase.getInstance(context)
        db.getEmployeeDao()
    }
    override suspend fun insertEmployee(employee: EmployeeModel) {
        employeeDao.insertEmployee(employee)
    }

    override fun getAllStoredEmployees(): Flow<List<EmployeeModel>> {
        return employeeDao.getAllEmployees()
    }

//    override suspend fun deleteEmployee(id: Int) {
//        employeeDao.deleteEmployee(id)
//    }

    override suspend fun insertSkill(skill: SkillModel) {
        skillsDao.insertSkill(skill)
    }

    override fun getAllStoredSkills(): Flow<List<SkillModel>> {
        return skillsDao.getAllSkills()
    }

//    override suspend fun deleteSkill(id: Int) {
//        skillsDao.deleteSkill(id)
//    }
}