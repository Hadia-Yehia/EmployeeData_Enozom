package com.example.employees_data.model

import com.example.employees_data.database.LocalSourceInterface
import kotlinx.coroutines.flow.Flow

class Repository(var localSourceInterface: LocalSourceInterface):RepositoryInterface {

    companion object {
        private var instance: Repository? = null
        fun getInstance(
            localSourceInterface: LocalSourceInterface
        ): RepositoryInterface {
            return instance ?: synchronized(this) {
                val temp = Repository(
                    localSourceInterface
                )
                instance = temp
                temp
            }
        }
    }
    override suspend fun insertEmployee(employee: EmployeeModel):Long {
        return localSourceInterface.insertEmployee(employee)
    }

    override fun getAllStoredEmployees(): Flow<List<EmployeeModel>> {
        return localSourceInterface.getAllStoredEmployees()
    }

//    override suspend fun deleteEmployee(id: Int) {
//        localSourceInterface.deleteEmployee(id)
//    }

    override suspend fun insertSkill(skill: SkillModel) {
        localSourceInterface.insertSkill(skill)
    }

    override fun getAllStoredSkills(): Flow<List<SkillModel>> {
        return  localSourceInterface.getAllStoredSkills()
    }

    override suspend fun insertEmployeeSkill(skill: EmployeeSkills) {
        localSourceInterface.insertEmployeeSkill(skill)
    }

    override fun getAllStoredEmployeeSkills(): Flow<List<EmployeeSkills>> {
        return getAllStoredEmployeeSkills()
    }

//    override suspend fun deleteSkill(id: Int) {
//        localSourceInterface.deleteSkill(id)
//    }
}