package com.ppk.graphql.di.module

import com.ppk.graphql.data.repoImpl.GetLaunchListRepoImpl
import com.ppk.graphql.domain.repository.GetLaunchListRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

import javax.inject.Singleton

@Module
@DisableInstallInCheck
abstract class GetLaunchListRepoModule {

    @Singleton
    @Binds
    abstract fun provideRepository(repoImpl: GetLaunchListRepoImpl): GetLaunchListRepository
}