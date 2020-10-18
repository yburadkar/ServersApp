package com.yb.serversapp.di

import com.yb.serversapp.data.di.NetworkModule
import com.yb.serversapp.features.status.dashboard.ServersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: ServersFragment)

}