package com.example.hiroshi.rxtubeapp.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.view.LayoutInflater
import com.example.hiroshi.rxtubeapp.MainViewModel
import com.example.hiroshi.rxtubeapp.ui.channeldetail.ChannelDetailViewModel
import com.example.hiroshi.rxtubeapp.ui.player.PlayerViewModel
import com.example.hiroshi.rxtubeapp.ui.searchcondition.SearchConditionViewModel
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created on 2018/03/14.
 */

var appModule = module {

    // ViewModel for Activity
    viewModel { MainViewModel() }

    // ViewModel for Fragment
    viewModel { SearchItemsViewModel(get(), get(), get(), get()) }
    viewModel { PlayerViewModel(get(), get()) }
    viewModel { SearchConditionViewModel(get(), get()) }
    viewModel { ChannelDetailViewModel() }

    single { androidApplication() }
    single { androidApplication().baseContext as Context }
    single { androidApplication().resources as Resources }
    single { LayoutInflater.from(get()) }
    single { androidApplication().baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

}