package com.example.employees_data.ui.home

import androidx.lifecycle.*
import com.example.employees_data.database.RoomState
import com.example.employees_data.model.EmployeeModel
import com.example.employees_data.model.EmployeeSkills
import com.example.employees_data.model.RepositoryInterface
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repo: RepositoryInterface) : ViewModel() {

    //    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
    private val skillsData: MutableStateFlow<RoomState> = MutableStateFlow(RoomState.Loading)
    var skillsObj: StateFlow<RoomState> = skillsData
    private val empIdData: MutableStateFlow<Long> = MutableStateFlow(0)
    var empIdObj: StateFlow<Long> = empIdData
    fun insertSkillInRoom(skill: SkillModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertSkill(skill)
        }
    }
    fun insertEmployeeInRoom(employee: EmployeeModel) {
        viewModelScope.launch { empIdData.value = repo.insertEmployee(employee) }

//        viewModelScope.launch(Dispatchers.IO) {
//            val result = repo.insertEmployee(employee)
//
//            withContext(Dispatchers.Main) {
//                result.catch { empIdData.value = RoomState.Failure(it) }
//                    .collect {
//                        empIdData.value = RoomState.SuccessEmployeeInsertion(it)
//                    }
//            }
//        }
    }
    fun insertEmployeeSkillInRoom(skill: EmployeeSkills) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertEmployeeSkill(skill)
        }
    }

    fun getSkillsFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getAllStoredSkills()

            withContext(Dispatchers.Main) {
                result.catch { skillsData.value = RoomState.Failure(it) }
                    .collect {
                        skillsData.value = RoomState.SuccessSkills(it)
                    }
            }
        }
    }
}
class HomeViewModelFactory(private val repo: RepositoryInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(repo) as T
        }else{
            throw java.lang.IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}