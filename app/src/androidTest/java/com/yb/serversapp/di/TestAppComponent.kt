package com.yb.serversapp.di

import com.yb.serversapp.data.di.TestNetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, TestNetworkModule::class, ViewModelModule::class])
interface TestAppComponent : AppComponent