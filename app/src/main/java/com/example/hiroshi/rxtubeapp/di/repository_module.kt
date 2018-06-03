package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepositoryImpl
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepositoryImpl
import org.koin.dsl.module.applicationContext

/**
 * Created on 2018/03/14.
 */

val repositoryModule = applicationContext {
    factory { YoutubeSearchRepositoryImpl(get(), get()) as YoutubeSearchRepository }
    factory { YoutubeSearchConditionRepositoryImpl(get()) as YoutubeSearchConditionRepository}
}