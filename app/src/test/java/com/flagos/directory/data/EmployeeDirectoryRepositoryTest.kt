package com.flagos.directory.data

import app.cash.turbine.test
import com.flagos.directory.MainCoroutinesRule
import com.flagos.directory.MockUtils.mockEmployeeDirectoryList
import com.flagos.directory.data.network.EmployeeDirectoryClient
import com.flagos.directory.data.network.EmployeeDirectoryService
import com.flagos.directory.domain.model.EmployeeDirectoryItem
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeDirectoryRepositoryTest {

    private lateinit var repository: EmployeeDirectoryRepository
    private lateinit var client: EmployeeDirectoryClient
    private val service: EmployeeDirectoryService = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = EmployeeDirectoryClient(service)
        repository = EmployeeDirectoryRepository(client, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchEmployeeDirectoryFromNetworkTest() = runTest {
        val mockData = EmployeeDirectoryItem(mockEmployeeDirectoryList())
        whenever(service.fetchEmployeeDirectoryList()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchEmployeeDirectoryList(onStart = {}, onError = {}, onComplete = {}).test {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.uuid, "0d8fcc12-4d0c-425c-8355-390b312b909c")
            assertEquals(expectItem.employeeType, "FULL_TIME")
            assertEquals(expectItem.biography, "Engineer on the Point of Sale team.")
            assertEquals(expectItem, mockEmployeeDirectoryList()[0])
            awaitComplete()
        }
    }
}
