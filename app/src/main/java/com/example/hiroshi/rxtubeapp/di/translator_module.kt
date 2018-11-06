package com.example.hiroshi.rxtubeapp.di

import com.example.hiroshi.rxtubeapp.data.repository.*
import com.example.hiroshi.rxtubeapp.ui.searchcondition.SearchConditionHistoryTranslator
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsAdapterTranslator
import org.koin.dsl.module.module

val translatorModule = module {
    factory { SearchConditionHistoryTranslator(get()) }
    factory { SearchItemsAdapterTranslator() }
}