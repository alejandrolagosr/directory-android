package com.flagos.directory.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.flagos.directory.MainCoroutinesRule
import com.flagos.directory.MockUtils.mockEmployeeDirectoryList
import com.flagos.directory.data.EmployeeDirectoryRepository
import com.flagos.directory.data.network.EmployeeDirectoryClient
import com.flagos.directory.data.network.EmployeeDirectoryService
import com.flagos.directory.domain.model.EmployeeDirectoryItem
import com.flagos.directory.presentation.MainViewModel.EmployeeDirectoryState.OnEmptyList
import com.flagos.directory.presentation.MainViewModel.EmployeeDirectoryState.OnListRetrieved
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
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: EmployeeDirectoryRepository
    private val service: EmployeeDirectoryService = mock()
    private val client: EmployeeDirectoryClient = EmployeeDirectoryClient(service)

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = EmployeeDirectoryRepository(client, coroutinesRule.testDispatcher)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun fetchEmployeeDirectoryList() = runTest {
        val mockData = EmployeeDirectoryItem(mockEmployeeDirectoryList())
        whenever(service.fetchEmployeeDirectoryList()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchEmployeeDirectoryList(onStart = {}, onComplete = {}, onError = {}).test {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.uuid, "0d8fcc12-4d0c-425c-8355-390b312b909c")
            assertEquals(expectItem.employeeType, "FULL_TIME")
            assertEquals(expectItem.biography, "Engineer on the Point of Sale team.")
            assertEquals(expectItem, mockEmployeeDirectoryList()[0])
            awaitComplete()
        }

        viewModel.fetchEmployees()

        assertEquals(OnListRetrieved(mockData.employees), viewModel.onEmployeeDirectoryState.value)
    }

    @Test
    fun fetchEmployeeDirectoryList_empty() = runTest {
        val mockData = EmployeeDirectoryItem(listOf())
        whenever(service.fetchEmployeeDirectoryList()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchEmployeeDirectoryList(onStart = {}, onComplete = {}, onError = {}).test {
            val expectItem = awaitItem()
            assertEquals(0, expectItem.size)
            awaitComplete()
        }

        viewModel.fetchEmployees()

        assertEquals(OnEmptyList, viewModel.onEmployeeDirectoryState.value)
    }
}