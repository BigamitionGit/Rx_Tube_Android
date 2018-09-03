package com.example.hiroshi.rxtubeapp.di

/**
 * Created by hosodahiroshi on 2018/09/03.
 */

import com.example.hiroshi.rxtubeapp.data.repository.*
import com.example.hiroshi.rxtubeapp.ui.searchcondition.SearchConditionHistoryTranslator
import org.koin.dsl.module.applicationContext

val translatorModule = applicationContext {
    factory { SearchConditionHistoryTranslator(get()) as SearchConditionHistoryTranslator }
}