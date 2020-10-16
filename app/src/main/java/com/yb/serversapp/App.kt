package com.yb.serversapp

import android.app.Application
import com.yb.serversapp.di.AppComponent
import com.yb.serversapp.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

open class App: Application() {

    open lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDi()
        initTimber()
    }

    private fun initDi() {
        appComponent = DaggerAppComponent.create()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG) Timber.plant(DebugTree())
    }

}