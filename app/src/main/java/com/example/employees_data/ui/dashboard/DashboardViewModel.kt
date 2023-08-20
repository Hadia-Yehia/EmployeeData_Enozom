package com.example.employees_data.ui.dashboard

import androidx.lifecycle.*
import com.example.employees_data.database.RoomState
import com.example.employees_data.model.RepositoryInterface
import com.example.employees_data.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(private val repo: RepositoryInterface) : ViewModel() {

private val employeesData: MutableStateFlow<RoomState> = MutableStateFlow(RoomState.Loading)
    var employeesObj: StateFlow<RoomState> = employeesData
    fun getEmployeesFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getAllStoredEmployees()

            withContext(Dispatchers.Main) {
                result.catch { employeesData.value = RoomState.Failure(it) }
                    .collect {
                        employeesData.value = RoomState.SuccessEmployee(it)
                    }
            }
        }
    }
}
class DashboardViewModelFactory(private val repo: RepositoryInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            DashboardViewModel(repo) as T
        }else{
            throw java.lang.IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}