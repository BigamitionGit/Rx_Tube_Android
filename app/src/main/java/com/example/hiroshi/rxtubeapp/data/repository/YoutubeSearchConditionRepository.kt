package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.data.db.model.*
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/03/19.
 */

interface YoutubeSearchConditionRepository {
    fun fetchSearchConditionHistory(): Maybe<YoutubeSearchConditionHistory>

    fun storeSearchCondition(model: YoutubeSearchCondition): Completable

    fun deleteSearchConditionHistory(): Completable

    fun deleteAllSearchConditionHistory(): Completable
}

class YoutubeSearchConditionRepositoryImpl(private val database: Database):YoutubeSearchConditionRepository {

    override fun fetchSearchConditionHistory(): Maybe<YoutubeSearchConditionHistory> {
        // Todo: userIdの取得
        val userId = ""
        val primaryKey = YoutubeSearchConditionHistory(userId, mutableListOf()).primaryKey()
        return database.fetchByPrimaryKey<YoutubeSearchConditionHistoryRealmModel, YoutubeSearchConditionHistory>(primaryKey)
    }

    override fun storeSearchCondition(model: YoutubeSearchCondition): Completable {
        // Todo: userIdの取得
        val userId = ""
        val newSearchConditionHistory = YoutubeSearchConditionHistory(userId, mutableListOf())
        return database.fetchByPrimaryKey<YoutubeSearchConditionHistoryRealmModel, YoutubeSearchConditionHistory>(newSearchConditionHistory.primaryKey())
                .map { searchConditionHisotry ->
                    if (searchConditionHisotry != null) {
                        searchConditionHisotry.history.add(model)
                        searchConditionHisotry
                    } else {
                        newSearchConditionHistory.history.add(model)
                        newSearchConditionHistory
                    }
                }
                .flatMapCompletable { database.store(it) }
    }

    override fun deleteSearchConditionHistory(): Completable {
        // Todo: userIdの取得
        val userId = ""
        val primaryKey = YoutubeSearchConditionHistory(userId, mutableListOf()).primaryKey()
        return database.deleteByPrimaryKey<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>(primaryKey)
    }

    override fun deleteAllSearchConditionHistory(): Completable {
        return database.deleteAll<YoutubeSearchConditionHistoryRealmModel, YoutubeSearchConditionHistory>()
    }
}