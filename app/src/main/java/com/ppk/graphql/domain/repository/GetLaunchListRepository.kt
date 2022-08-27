package com.ppk.graphql.domain.repository

import androidx.lifecycle.MutableLiveData
import com.ppk.graphql.data.model.LaunchModel
import javax.inject.Inject
import javax.inject.Singleton


interface GetLaunchListRepository {
    fun getLaunchList():MutableLiveData<ArrayList<LaunchModel>>
}