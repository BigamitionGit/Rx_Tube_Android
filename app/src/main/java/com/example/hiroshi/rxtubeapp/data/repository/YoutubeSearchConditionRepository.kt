package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.data.db.model.RealmModelPrimaryKey
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchConditionRealmModel
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/03/19.
 */

interface YoutubeSearchConditionRepository {
    fun getSearchCondition(p: RealmModelPrimaryKey): Maybe<YoutubeSearchCondition>
}

class YoutubeSearchConditionRepositoryImpl(private val database: Database):YoutubeSearchConditionRepository {

    override fun getSearchCondition(p: RealmModelPrimaryKey): Maybe<YoutubeSearchCondition> {
        return database.fetchByPrimaryKey<YoutubeSearchConditionRealmModel, YoutubeSearchCondition>(p)
    }
}