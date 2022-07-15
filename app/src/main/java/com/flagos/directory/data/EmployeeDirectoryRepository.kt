package com.flagos.directory.data

import androidx.annotation.WorkerThread
import com.flagos.directory.data.network.EmployeeDirectoryClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class EmployeeDirectoryRepository @Inject constructor(
    private val employeeDirectoryClient: EmployeeDirectoryClient,
    private val ioDispatcher: CoroutineDispatcher
) {

    @WorkerThread
    fun fetchEmployeeDirectoryList(onStart: () -> Unit, onComplete: () -> Unit, onError: (String?) -> Unit) = flow {
        employeeDirectoryClient.fetchEmployeeDirectoryList()
            .suspendOnSuccess { emit(data.employees) }
            .onFailure { onError(message()) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
