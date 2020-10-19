package com.yb.serversapp.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestAppModule {

    @Singleton
    @Provides
    @Named("io")
    fun ioScheduler(): Scheduler = Schedulers.trampoline()

    @Singleton
    @Provides
    @Named("ui")
    fun uiScheduler(): Scheduler = Schedulers.trampoline()

}