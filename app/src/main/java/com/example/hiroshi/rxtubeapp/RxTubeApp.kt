package com.example.hiroshi.rxtubeapp

import android.app.Application
import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.di.*
import com.example.hiroshi.rxtubeapp.di.activity.activityModule
import org.koin.android.ext.android.startKoin

/**
 * Created by on 2018/03/09.
 */
class RxTubeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this,
                listOf(
                apiModule,
                dbModule,
                appModule,
                networkModule,
                repositoryModule) + activityModule
        )

        val db = Database()
        db.config(this)

    }
}