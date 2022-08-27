package com.ppk.graphql.data.repoImpl

import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.data.model.LaunchModel
import com.ppk.graphql.domain.repository.GetLaunchListRepository
import com.ppk.graphql.util.DefaultConfig


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


class GetLaunchListRepoImpl @Inject constructor() : GetLaunchListRepository {

    @Inject
    lateinit var apollo: ApolloClient

    override fun getLaunchList(): MutableLiveData<ArrayList<LaunchModel>> {
        TODO("")
    }


    private val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }


    private fun getApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl(DefaultConfig.URL)
            .okHttpClient(httpClient)
            .build()
    }

    //apply(plugin = "dagger.hilt.android.plugin")
    val query = getApolloClient().query(CharacterQuery())
}