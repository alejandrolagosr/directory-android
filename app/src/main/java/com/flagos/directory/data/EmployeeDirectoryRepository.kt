package com.flagos.directory.data

import androidx.annotation.WorkerThread
import com.flagos.directory.data.network.EmployeeDirectoryClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class EmployeeDirectoryRepository @Inject constructor(
    private val employeeDirectoryClient: EmployeeDirectoryClient,
    private val ioDispatcher: CoroutineDispatcher
) {

    @WorkerThread
    fun fetchEmployeeDirectoryList(onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit) = flow {
        employeeDirectoryClient.fetchEmployeeDirectoryList()
            .suspendOnSuccess {
                emit(data.employees)
            }.onFailure {
                onError(message())
            }
    }.onStart {
        onStart()
    }.onCompletion {
        delay(300)
        onComplete()
    }.flowOn(ioDispatcher).conflate()
}
