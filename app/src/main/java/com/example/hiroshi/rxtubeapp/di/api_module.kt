package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApi
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.mapper.ApplicationJsonAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created on 2018/03/14.
 */

val apiModule = applicationContext {
    bean { MoshiConverterFactory.create(Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .build()) }
    bean { RxJava2CallAdapterFactory.create() }

    bean { Retrofit.Builder()
            .baseUrl(YoutubeApi.baseURL)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(YoutubeApiService::class.java) }
}