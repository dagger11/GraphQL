package com.ppk.graphql.domain.interactor

import androidx.lifecycle.MutableLiveData
import com.ppk.graphql.data.model.LaunchModel
import com.ppk.graphql.domain.repository.GetLaunchListRepository

import javax.inject.Inject
import javax.inject.Singleton


class GetLaunchListUseCase constructor(repository: GetLaunchListRepository) {
    val repo = repository
    fun getLaunchList():MutableLiveData<ArrayList<LaunchModel>> = repo.getLaunchList()
}