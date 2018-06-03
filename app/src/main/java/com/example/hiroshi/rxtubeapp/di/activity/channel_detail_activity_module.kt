package com.example.hiroshi.rxtubeapp.di.activity

import android.support.v7.app.AppCompatActivity
import com.example.hiroshi.rxtubeapp.MainActivity
import com.example.hiroshi.rxtubeapp.NavigationController
import com.example.hiroshi.rxtubeapp.ui.channelDetail.ChannelDetailActivity
import org.koin.dsl.module.applicationContext

var channelDetailActivityModule = applicationContext {

    factory("channel") { NavigationController(get()) as NavigationController }
    bean { ChannelDetailActivity() as AppCompatActivity }
}