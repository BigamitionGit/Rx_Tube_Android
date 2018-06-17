package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.data.db.model.RealmModelPrimaryKey
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchConditionRealmModel
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
    fun fetchSearchCondition(primaryKey: RealmModelPrimaryKey): Maybe<YoutubeSearchCondition>

    fun fetchAllSearchCondition(): Observable<YoutubeSearchCondition>

    fun storeSearchCondition(model: YoutubeSearchCondition): Completable

    fun deleteSearchCondition(primaryKey: RealmModelPrimaryKey): Completable

    fun deleteAllSearchCondition(): Completable
}

class YoutubeSearchConditionRepositoryImpl(private val database: Database):YoutubeSearchConditionRepository {

    override fun fetchSearchCondition(primaryKey: RealmModelPrimaryKey): Maybe<YoutubeSearchCondition> {
        return database.fetchByPrimaryKey<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>(primaryKey)
    }

    override fun fetchAllSearchCondition(): Observable<YoutubeSearchCondition> {
        return database.fetchAll<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>()
    }

    override fun storeSearchCondition(model: YoutubeSearchCondition): Completable {
        return database.store(model)
    }

    override fun deleteSearchCondition(primaryKey: RealmModelPrimaryKey): Completable {
        return database.deleteByPrimaryKey<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>(primaryKey)
    }

    override fun deleteAllSearchCondition(): Completable {
        return database.deleteAll<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>()
    }
}