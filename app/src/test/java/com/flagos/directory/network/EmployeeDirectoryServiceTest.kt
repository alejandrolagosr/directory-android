package com.flagos.directory.network

import com.flagos.directory.data.network.EmployeeDirectoryService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeDirectoryServiceTest : ApiAbstract<EmployeeDirectoryService>() {

    private lateinit var service: EmployeeDirectoryService

    @Before
    fun initService() {
        service = createService(EmployeeDirectoryService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchEmployeeDirectoryListFromNetwork() = runTest {
        enqueueResponse("/employees.json")
        val response = service.fetchEmployeeDirectoryList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.employees.size, `is`(11))
        assertThat(responseBody.employees[0].fullName, `is`("Justine Mason"))
        assertThat(responseBody.employees[0].uuid, `is`("0d8fcc12-4d0c-425c-8355-390b312b909c"))
    }

    @Throws(IOException::class)
    @Test
    fun fetchEmployeeDirectoryListFromNetwork_empty() = runTest {
        enqueueResponse("/employees_empty.json")
        val response = service.fetchEmployeeDirectoryList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.employees.size, `is`(0))
    }

    @Throws(IOException::class)
    @Test
    fun fetchEmployeeDirectoryListFromNetwork_malformed() = runTest {
        enqueueResponse("/employees_malformed.json")
        val response = service.fetchEmployeeDirectoryList()
        val responseBody = requireNotNull((response as ApiResponse.Failure))

        assertThat(
            responseBody.message(),
            `is`("[ApiResponse.Failure.Exception](message=Required value 'team' missing at \$.employees[2])")
        )
    }
}
