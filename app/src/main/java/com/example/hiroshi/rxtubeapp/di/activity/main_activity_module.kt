package com.example.hiroshi.rxtubeapp.di.activity

import android.support.v7.app.AppCompatActivity
import com.example.hiroshi.rxtubeapp.MainActivity
import com.example.hiroshi.rxtubeapp.NavigationController
import org.koin.dsl.module.applicationContext


var mainActivityModule = applicationContext {

    factory("main") { NavigationController(get()) as NavigationController }
    bean { MainActivity() as AppCompatActivity }
}