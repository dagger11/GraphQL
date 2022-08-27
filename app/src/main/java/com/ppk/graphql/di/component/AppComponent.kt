package com.ppk.graphql.di.component

import com.ppk.graphql.di.module.AppModule
import com.ppk.graphql.presentation.viewModel.MainActivityViewModel
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}