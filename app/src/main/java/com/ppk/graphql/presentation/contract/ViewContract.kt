package com.ppk.graphql.presentation.contract

import android.content.Context


interface ViewContract {
    fun nextActivity(id:String)

    fun provideContext():Context

    fun showToast(message:String)
}