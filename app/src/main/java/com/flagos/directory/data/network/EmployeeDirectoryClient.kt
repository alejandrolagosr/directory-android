package com.flagos.directory.data.network

import com.flagos.directory.domain.model.EmployeeDirectoryItem
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class EmployeeDirectoryClient @Inject constructor(private val employeeDirectoryService: EmployeeDirectoryService) {

    suspend fun fetchEmployeeDirectoryList(): ApiResponse<EmployeeDirectoryItem> = employeeDirectoryService.fetchEmployeeDirectoryList()
}
