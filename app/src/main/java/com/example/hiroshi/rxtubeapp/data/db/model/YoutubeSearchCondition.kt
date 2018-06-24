package com.example.hiroshi.rxtubeapp.data.db.model

import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created on 2018/03/21.
 */

@RealmClass
open class YoutubeSearchConditionRealmModel(
        open var searchQuery: String?,
        open var channelId: String?,
        open var eventType: String?,
        open var orderType: String?,
        open var publishedAfter: Date?,
        open var publishedBefore: Date?,
        open var regionCode: String?,
        open var type: String?,
        open var videoCaption: String?,
        open var videoCategoryid: String?,
        open var videoDefinition: String?,
        open var videoDuration: String?


): ConvertibleRealmModel<YoutubeSearchCondition> {

    override fun toModel(): YoutubeSearchCondition {
        return YoutubeSearchCondition(
                searchQuery,
                channelId,
                eventType,
                orderType,
                publishedAfter,
                publishedBefore,
                regionCode,
                type,
                videoCaption,
                videoCategoryid,
                videoDefinition,
                videoDuration)
    }
}

data class YoutubeSearchCondition(
        val searchQuery: String?,
        val channelId: String?,
        val eventType: String?,
        val orderType: String?,
        val publishedAfter: Date?,
        val publishedBefore: Date?,
        val regionCode: String?,
        val type: String?,
        val videoCaption: String?,
        val videoCategoryid: String?,
        val videoDefinition: String?,
        val videoDuration: String?

): ConvertibleToRealmModel<YoutubeSearchConditionRealmModel> {

    override fun toRealmModel(): YoutubeSearchConditionRealmModel {
        return YoutubeSearchConditionRealmModel(
                searchQuery,
                channelId,
                eventType,
                orderType,
                publishedAfter,
                publishedBefore,
                regionCode,
                type,
                videoCaption,
                videoCategoryid,
                videoDefinition,
                videoDuration)
    }
}