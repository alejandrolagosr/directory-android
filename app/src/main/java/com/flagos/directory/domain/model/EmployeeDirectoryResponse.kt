package com.flagos.directory.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeDirectoryResponse(@field:Json(name = "employees") val employees: List<EmployeeResponse> = listOf())

@JsonClass(generateAdapter = true)
data class EmployeeResponse(
    @field:Json(name = "uuid") val uuid: String,
    @field:Json(name = "full_name") val fullName: String,
    @field:Json(name = "phone_number") val phoneNumber: String,
    @field:Json(name = "email_address") val emailAddress: String,
    @field:Json(name = "biography") val biography: String,
    @field:Json(name = "photo_url_small") val photoUrlSmall : String,
    @field:Json(name = "photo_url_large") val photoUrlLarge: String,
    @field:Json(name = "team") val team: String,
    @field:Json(name = "employee_type") val employeeType: String,
)
