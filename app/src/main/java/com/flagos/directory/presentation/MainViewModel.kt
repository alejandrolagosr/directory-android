package com.flagos.directory.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flagos.directory.data.EmployeeDirectoryRepository
import com.flagos.directory.domain.model.EmployeeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val employeeDirectoryRepository: EmployeeDirectoryRepository) : ViewModel() {

    private var _onEmployeeDirectoryListRetrieved = MutableLiveData<List<EmployeeItem>>()
    val onEmployeeDirectoryListRetrieved get() = _onEmployeeDirectoryListRetrieved

    private var _onLoading = MutableLiveData<Boolean>()
    val onLoading get() = _onLoading

    private var _onError = MutableLiveData<String>()
    val onError get() = _onError

    fun fetchEmployees() {
            val employees = employeeDirectoryRepository.fetchEmployeeDirectoryList(
                onStart = { _onLoading.postValue(true) },
                onComplete = { _onLoading.postValue(false) },
                onError = { onError.postValue(it) }
            )

        viewModelScope.launch {
            employees.distinctUntilChanged().collect {
                _onEmployeeDirectoryListRetrieved.value = it
            }
        }
    }
}
