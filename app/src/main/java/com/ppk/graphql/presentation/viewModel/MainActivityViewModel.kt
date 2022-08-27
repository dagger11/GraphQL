package com.ppk.graphql.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.ppk.graphql.BaseApplication
import com.ppk.graphql.domain.interactor.GetLaunchListUseCase

import javax.inject.Inject

class MainActivityViewModel : ViewModel() {

    @Inject
    lateinit var useCase: GetLaunchListUseCase

}