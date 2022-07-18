package com.flagos.directory.data.network

import com.flagos.directory.domain.model.EmployeeDirectoryItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface EmployeeDirectoryService {

    @GET("employees.json")
    suspend fun fetchEmployeeDirectoryList() : ApiResponse<EmployeeDirectoryItem>
}
