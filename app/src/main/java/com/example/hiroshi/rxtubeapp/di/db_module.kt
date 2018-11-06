package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import org.koin.dsl.module.module

/**
 * Created on 2018/03/21.
 */


var dbModule = module {
    single { Database() }
}