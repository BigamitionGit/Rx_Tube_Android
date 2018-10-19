package com.example.hiroshi.rxtubeapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractorImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created on 2018/03/14.
 */

var networkModule = module {

    single { androidApplication().baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    single { NetworkInteractorImpl(get()) as NetworkInteractor }
}