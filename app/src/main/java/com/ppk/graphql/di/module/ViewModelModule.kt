package com.ppk.graphql.di.module

import com.ppk.graphql.data.repoImpl.GetCharacterListRepoImpl
import com.ppk.graphql.domain.repository.GetCharacterListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun provideRepository(getLaunchListRepoImpl: GetCharacterListRepoImpl):GetCharacterListRepository
}