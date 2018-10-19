package com.example.hiroshi.rxtubeapp.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.hiroshi.rxtubeapp.MainActivity
import com.example.hiroshi.rxtubeapp.NavigationController
import org.koin.dsl.module.module


var mainActivityModule = module {

    factory("main") { NavigationController(get()) as NavigationController }
    single { MainActivity() as AppCompatActivity }
}