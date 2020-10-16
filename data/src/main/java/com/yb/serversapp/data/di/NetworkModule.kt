package com.yb.serversapp.data.di

import com.yb.serversapp.data.remote.ServerStatusService
import com.yb.serversapp.data.remote.repos.ServerStatusRepository
import com.yb.serversapp.domain.repos.IServerStatusRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideServerStatusService(retrofit: Retrofit): ServerStatusService {
        return retrofit.create(ServerStatusService::class.java)
    }

    @Singleton
    @Provides
    fun provideServerStatusRepository(serverStatusRepo: ServerStatusRepository): IServerStatusRepository = serverStatusRepo

    companion object {
        private const val BASE_URL = "https://private-176645-utilita.apiary-mock.com/"
    }

}