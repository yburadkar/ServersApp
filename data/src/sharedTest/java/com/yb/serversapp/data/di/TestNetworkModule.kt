package com.yb.serversapp.data.di

import com.yb.serversapp.data.remote.ServerStatusService
import com.yb.serversapp.domain.repos.IServerStatusRepository
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestNetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = mockk()

    @Singleton
    @Provides
    fun provideServerStatusService(): ServerStatusService = mockk()

    @Singleton
    @Provides
    fun provideServerStatusRepository(): IServerStatusRepository = mockk()

}