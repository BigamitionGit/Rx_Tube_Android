package com.example.hiroshi.rxtubeapp.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.example.hiroshi.rxtubeapp.MainActivity
import com.example.hiroshi.rxtubeapp.NavigationController
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepositoryImpl
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepositoryImpl
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

/**
 * Created on 2018/03/14.
 */

var appModule = applicationContext {

    // ViewModel for SearchItemsFragment
    viewModel { SearchItemsViewModel(get(), get()) }

    bean { androidApplication() as Application }
    bean { androidApplication().baseContext as Context }
    bean { androidApplication().resources as Resources }
    bean { LayoutInflater.from(get()) }
    bean { androidApplication().baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

}