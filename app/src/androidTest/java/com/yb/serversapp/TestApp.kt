package com.yb.serversapp

import com.yb.serversapp.di.AppComponent
import com.yb.serversapp.di.DaggerTestAppComponent

class TestApp: App() {

    override var appComponent: AppComponent = DaggerTestAppComponent.create()

}