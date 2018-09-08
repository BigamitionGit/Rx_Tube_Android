package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.repository.*
import org.koin.dsl.module.applicationContext

/**
 * Created on 2018/03/14.
 */

val repositoryModule = applicationContext {
    factory { YoutubeSearchRepositoryImpl(get(), get()) as YoutubeSearchRepository }
    factory { YoutubeSearchConditionRepositoryImpl(get()) as YoutubeSearchConditionRepository}
    factory { YoutubeSearchDetailRepositoryImpl(get(), get()) as YoutubeSearchDetailRepository }
    factory { YoutubeRelatedVideosRepositoryImpl(get(), get()) as YoutubeRelatedVideosRepository }
    factory { YoutubeVideosRepositoryImpl(get(), get()) as YoutubeVideosRepository }
    factory { YoutubeVideosRateRepositoryImpl(get(), get()) as YoutubeVideosRateRepository }
}