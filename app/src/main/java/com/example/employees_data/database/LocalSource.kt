package com.example.employees_data.database

import android.content.Context
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
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
    private val employeeSkillsDao:EmployeeSkillDao by lazy {
        val db:DataBase=DataBase.getInstance(context)
        db.getEmployeeSkillsDao()
    }
    override suspend fun insertEmployee(employee: EmployeeModel):Long {
       return employeeDao.insertEmployee(employee)
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

    override suspend fun insertEmployeeSkill(skill: EmployeeSkills) {
        employeeSkillsDao.insertEmployeeSkill(skill)
    }

    override fun getAllStoredSkillsForEmployee(): Flow<List<EmployeeSkills>> {
        return employeeSkillsDao.getAllEmployeeSkills()
    }

//    override suspend fun deleteSkill(id: Int) {
//        skillsDao.deleteSkill(id)
//    }
}