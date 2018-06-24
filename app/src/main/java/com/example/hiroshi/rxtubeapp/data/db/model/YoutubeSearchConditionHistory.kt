package com.example.hiroshi.rxtubeapp.data.db.model

import io.realm.RealmList
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by hosodahiroshi on 2018/06/24.
 */

@RealmClass
open class YoutubeSearchConditionHistoryRealmModel(
        @PrimaryKey open val userId: String,
        open var hisotry: RealmList<YoutubeSearchConditionRealmModel>): ConvertibleRealmModel<YoutubeSearchConditionHistory> {

    override fun toModel(): YoutubeSearchConditionHistory {
        val historyModel = hisotry.map { it.toModel() }.toMutableList()
        return YoutubeSearchConditionHistory(userId, historyModel)
    }
}

data class YoutubeSearchConditionHistory(
        val userId: String,
        val history: MutableList<YoutubeSearchCondition>
): ConvertibleToRealmModel<YoutubeSearchConditionHistoryRealmModel>, RealmModelPrimaryKeyType {

    override fun toRealmModel(): YoutubeSearchConditionHistoryRealmModel {
        val realmList = RealmList<YoutubeSearchConditionRealmModel>()
        realmList.addAll(history.map { it.toRealmModel() })

        return YoutubeSearchConditionHistoryRealmModel(userId, realmList)
    }

    override fun primaryKey(): RealmModelPrimaryKey {
        val value = RealmModelPrimaryKey.ValueType.String(userId)
        return RealmModelPrimaryKey("userId", value)
    }
}