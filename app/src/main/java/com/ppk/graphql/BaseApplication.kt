package com.ppk.graphql

import android.app.Application
import com.ppk.graphql.di.component.AppComponent
import com.ppk.graphql.di.component.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    fun initAppComponent(){
        appComponent = DaggerAppComponent.create()
    }


    fun get(): AppComponent {
        return appComponent
    }
}