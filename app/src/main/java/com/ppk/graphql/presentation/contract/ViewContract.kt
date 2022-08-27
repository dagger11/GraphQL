package com.ppk.graphql.presentation.contract

import com.ppk.graphql.data.model.LaunchModel


interface ViewContract {
    fun nextActivity(launchModel: LaunchModel)

    fun showToast(message:String)
}