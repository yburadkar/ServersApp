package com.yb.serversapp.di

import com.yb.serversapp.features.main.MainActivity
import com.yb.serversapp.data.di.NetworkModule
import com.yb.serversapp.features.main.ServersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ServersFragment)

}