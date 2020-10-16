package com.yb.serversapp.di

import com.yb.serversapp.MainActivity
import com.yb.serversapp.data.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

}