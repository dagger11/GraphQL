package com.ppk.graphql.di.module

import com.ppk.graphql.domain.interactor.GetLaunchListUseCase
import com.ppk.graphql.domain.repository.GetCharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideUseCase(getCharacterListRepository: GetCharacterListRepository): GetLaunchListUseCase =
        GetLaunchListUseCase(getCharacterListRepository)
}