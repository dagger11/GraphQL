package com.ppk.graphql.data.repoImpl

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.network.okHttpClient
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.CharactersListQuery
import com.ppk.graphql.domain.repository.GetCharacterListRepository
import com.ppk.graphql.util.DefaultConfig


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit
import javax.inject.Inject


class GetCharacterListRepoImpl @Inject constructor() : GetCharacterListRepository {

    @Inject
    lateinit var apollo: ApolloClient

//    private val httpClient: OkHttpClient by lazy {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .callTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(httpLoggingInterceptor).build()
//    }
//
//
//    private fun apollo(): ApolloClient {
//        return ApolloClient.Builder().serverUrl(DefaultConfig.URL)
//            .okHttpClient(httpClient)
//            .build()
//    }


    override suspend fun getCharacterList(): ApolloResponse<CharactersListQuery.Data> {
        return apollo.query(CharactersListQuery()).execute()
    }

    override suspend fun getCharacter(id: String): ApolloResponse<CharacterQuery.Data> {
        return apollo.query(CharacterQuery(id)).execute()
    }
}