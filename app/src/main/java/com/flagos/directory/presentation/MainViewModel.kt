package com.flagos.directory.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flagos.directory.data.EmployeeDirectoryRepository
import com.flagos.directory.domain.model.EmployeeItem
import com.flagos.directory.presentation.MainViewModel.EmployeeDirectoryState.*
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

    private var _onEmployeeDirectoryState = MutableLiveData<EmployeeDirectoryState>()
    val onEmployeeDirectoryState get() = _onEmployeeDirectoryState

    fun fetchEmployees() {
            val employees = employeeDirectoryRepository.fetchEmployeeDirectoryList(
                onStart = { _onEmployeeDirectoryState.postValue(OnLoading(true)) },
                onComplete = { _onEmployeeDirectoryState.postValue(OnLoading(false)) },
                onError = { _onEmployeeDirectoryState.postValue(OnError(it)) }
            )

        viewModelScope.launch {
            employees.distinctUntilChanged().collect { list ->
                _onEmployeeDirectoryState.value = if (list.isNotEmpty()) OnListRetrieved(list) else OnEmptyList
            }
        }
    }

    sealed class EmployeeDirectoryState {
        data class OnLoading(val loading: Boolean) : EmployeeDirectoryState()
        data class OnError(val error: String?) : EmployeeDirectoryState()
        data class OnListRetrieved(val list : List<EmployeeItem>): EmployeeDirectoryState()
        object OnEmptyList : EmployeeDirectoryState()
    }
}
