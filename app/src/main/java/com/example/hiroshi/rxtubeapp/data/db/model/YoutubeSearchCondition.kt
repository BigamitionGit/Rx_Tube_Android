package com.example.hiroshi.rxtubeapp.data.db.model

import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created on 2018/03/21.
 */

@RealmClass
open class YoutubeSearchConditionRealmModel(
        @PrimaryKey open val userId: String,
        open var searchQuery: String
): ConvertibleRealmModel<YoutubeSearchCondition> {

    override fun toModel(): YoutubeSearchCondition {
        return YoutubeSearchCondition(userId, searchQuery)
    }
}

data class YoutubeSearchCondition(
        val userId: String,
        val searchQuery: String
): ConvertibleToRealmModel<YoutubeSearchConditionRealmModel>, RealmModelPrimaryKeyType {

    override fun toRealmModel(): YoutubeSearchConditionRealmModel {
        return YoutubeSearchConditionRealmModel(userId, searchQuery)
    }

    override fun primaryKey(): RealmModelPrimaryKey {
        val value = RealmModelPrimaryKey.ValueType.String(userId)
        return RealmModelPrimaryKey("userId", value)
    }
}