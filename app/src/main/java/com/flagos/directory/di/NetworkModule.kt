package com.flagos.directory.di

import com.flagos.directory.data.network.EmployeeDirectoryClient
import com.flagos.directory.data.network.EmployeeDirectoryService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeDirectoryService(retrofit: Retrofit): EmployeeDirectoryService = retrofit.create(EmployeeDirectoryService::class.java)

    @Provides
    @Singleton
    fun provideEmployeeDirectoryClient(employeeDirectoryService: EmployeeDirectoryService): EmployeeDirectoryClient {
        return EmployeeDirectoryClient(employeeDirectoryService)
    }
}
