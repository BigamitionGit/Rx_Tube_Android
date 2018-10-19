package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.mapper.ApplicationJsonAdapterFactory
import com.example.hiroshi.rxtubeapp.data.remote.model.mapper.SearchItemIdAdapter
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created on 2018/03/14.
 */

val apiModule = module {
    single { MoshiConverterFactory.create(Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .add(SearchItemIdAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()) }
    single { RxJava2CallAdapterFactory.create() }

    single { Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(YoutubeApiService::class.java) }
}