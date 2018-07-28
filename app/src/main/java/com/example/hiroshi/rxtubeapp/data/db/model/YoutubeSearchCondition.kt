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
        open var event: String?,
        open var orderType: String?,
        open var publishedAfter: Date?,
        open var publishedBefore: Date?,
        open var regionCode: String?,
        open var searchType: String?,
        open var caption: String?,
        open var videoCategoryid: String?,
        open var definition: String?,
        open var duration: String?


): ConvertibleRealmModel<YoutubeSearchCondition> {

    override fun toModel(): YoutubeSearchCondition {
        return YoutubeSearchCondition(
                searchQuery,
                channelId,
                event,
                orderType,
                publishedAfter,
                publishedBefore,
                regionCode,
                searchType,
                caption,
                videoCategoryid,
                definition,
                duration)
    }
}

data class YoutubeSearchCondition(
        val searchQuery: String?,
        val channelId: String?,
        val event: String?,
        val orderType: String?,
        val publishedAfter: Date?,
        val publishedBefore: Date?,
        val regionCode: String?,
        val searchType: String?,
        val caption: String?,
        val videoCategoryid: String?,
        val definition: String?,
        val duration: String?

): ConvertibleToRealmModel<YoutubeSearchConditionRealmModel> {

    override fun toRealmModel(): YoutubeSearchConditionRealmModel {
        return YoutubeSearchConditionRealmModel(
                searchQuery,
                channelId,
                event,
                orderType,
                publishedAfter,
                publishedBefore,
                regionCode,
                searchType,
                caption,
                videoCategoryid,
                definition,
                duration)
    }

    fun toYoutubeSearchOptionParameter(): Set<YoutubeApiParameter.Option.Search> {

        val searchQParam = if (searchQuery != null) YoutubeApiParameter.Option.Search.Q(searchQuery) else null
        val channelIdParam = if (channelId != null) YoutubeApiParameter.Option.Search.ChannelId(channelId) else null
        val eventTypeParam = if (event != null) YoutubeApiParameter.Option.Search.EventType(YoutubeApiParameter.Option.Search.Event.valueOf(event)) else null
        val orderParam = if (orderType != null) YoutubeApiParameter.Option.Search.Order(YoutubeApiParameter.Option.Search.OrderType.valueOf(orderType)) else null
        val publishedAfterParam = if (publishedAfter != null) YoutubeApiParameter.Option.Search.PublishedAfter(publishedAfter) else null
        val publishedBeforeParam = if (publishedBefore != null) YoutubeApiParameter.Option.Search.PublishedBefore(publishedBefore) else null
        val regionCodeParam = if (regionCode != null) YoutubeApiParameter.Option.Search.RegionCode(YoutubeApiParameter.Option.RegionCode(YoutubeApiParameter.Option.RegionCode.Region.valueOf(regionCode))) else null
        val typeParam = if (searchType != null) YoutubeApiParameter.Option.Search.Type(YoutubeApiParameter.Option.Search.SearchType.valueOf(searchType)) else null
        val videoCaptionParam = if (caption != null) YoutubeApiParameter.Option.Search.VideoCaption(YoutubeApiParameter.Option.Search.Caption.valueOf(caption)) else null
        val videoCategoryidParam = if (videoCategoryid != null) YoutubeApiParameter.Option.Search.VideoCategoryId(videoCategoryid) else null
        val videoDefinitionParam = if (definition != null) YoutubeApiParameter.Option.Search.VideoDefinition(YoutubeApiParameter.Option.Search.Definition.valueOf(definition)) else null
        val videoDurationParam = if (duration != null) YoutubeApiParameter.Option.Search.VideoDuration(YoutubeApiParameter.Option.Search.Duration.valueOf(duration)) else null


        return setOf<YoutubeApiParameter.Option.Search?>(
                searchQParam,
                channelIdParam,
                eventTypeParam,
                orderParam,
                publishedAfterParam,
                publishedBeforeParam,
                regionCodeParam,
                typeParam,
                videoCaptionParam,
                videoCategoryidParam,
                videoDefinitionParam,
                videoDurationParam
        )
                .filterNotNull()
                .toSet()
    }
}