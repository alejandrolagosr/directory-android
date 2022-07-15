package com.flagos.directory.di

import com.flagos.directory.data.EmployeeDirectoryRepository
import com.flagos.directory.data.network.EmployeeDirectoryClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideEmployeeDirectoryRepository(
        employeeDirectoryClient: EmployeeDirectoryClient,
        ioDispatcher: CoroutineDispatcher
    ): EmployeeDirectoryRepository = EmployeeDirectoryRepository(employeeDirectoryClient, ioDispatcher)
}
