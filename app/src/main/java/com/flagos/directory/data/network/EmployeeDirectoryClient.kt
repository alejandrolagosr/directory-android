package com.flagos.directory.data.network

import com.flagos.directory.domain.model.EmployeeDirectoryResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class EmployeeDirectoryClient @Inject constructor(private val employeeDirectoryService: EmployeeDirectoryService) {

    suspend fun fetchEmployeeDirectoryList(): ApiResponse<EmployeeDirectoryResponse> = employeeDirectoryService.fetchEmployeeDirectoryList()
}
