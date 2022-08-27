package com.ppk.graphql.di.module

import android.app.Application
import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.ppk.graphql.BaseApplication
import com.ppk.graphql.util.DefaultConfig


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [LaunchListModule::class])
@DisableInstallInCheck

object AppModule {

    @Singleton
    @Provides
    fun provideApplication( app: Context): Application {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideApollo(): ApolloClient{
        return ApolloClient.Builder().serverUrl(DefaultConfig.URL)
            .okHttpClient(httpClient)
            .build()
    }

    private val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

}