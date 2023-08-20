package com.example.employees_data.ui.home

import androidx.lifecycle.*
import com.example.employees_data.model.RepositoryInterface
import com.example.employees_data.model.SkillModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: RepositoryInterface) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    fun insertSkillInRoom(skill:SkillModel){
        viewModelScope.launch  (Dispatchers.IO){
            repo.insertSkill(skill)
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