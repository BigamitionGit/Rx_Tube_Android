package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.repository.*
import com.example.hiroshi.rxtubeapp.ui.searchcondition.SearchConditionHistoryTranslator
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsAdapterTranslator
import org.koin.dsl.module.applicationContext

val translatorModule = applicationContext {
    factory { SearchConditionHistoryTranslator(get()) as SearchConditionHistoryTranslator }
    factory { SearchItemsAdapterTranslator() as SearchItemsAdapterTranslator }
}