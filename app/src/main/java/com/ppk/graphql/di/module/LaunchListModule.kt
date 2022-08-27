package com.ppk.graphql.di.module

import com.ppk.graphql.domain.interactor.GetLaunchListUseCase
import com.ppk.graphql.domain.repository.GetLaunchListRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

import javax.inject.Singleton

@DisableInstallInCheck
@Module(includes = [GetLaunchListRepoModule::class])
class LaunchListModule {

    @Singleton
    @Provides
    fun provideUseCase(getLaunchListRepository: GetLaunchListRepository): GetLaunchListUseCase =
        GetLaunchListUseCase(getLaunchListRepository)
}