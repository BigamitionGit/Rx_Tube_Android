package com.example.hiroshi.rxtubeapp.data.db.model

import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import io.realm.RealmList
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
        open var searchTypes: RealmList<String>,
        open var caption: String?,
        open var videoCategoryid: String?,
        open var definition: String?,
        open var duration: String?


): ConvertibleRealmModel<YoutubeSearchCondition> {

    override fun toModel(): YoutubeSearchCondition {
        return YoutubeSearchCondition(
                searchQuery,
                channelId,
                event?.let { YoutubeApiParameter.Option.Search.Event.valueOf(it) },
                orderType?.let { YoutubeApiParameter.Option.Search.OrderType.valueOf(it) },
                publishedAfter,
                publishedBefore,
                regionCode?.let { YoutubeApiParameter.Option.RegionCode.valueOf(it) },
                searchTypes.mapNotNull { type -> type?.let { YoutubeApiParameter.Option.Search.SearchType.valueOf(it) } }.toSet(),
                caption?.let { YoutubeApiParameter.Option.Search.Caption.valueOf(it) },
                videoCategoryid,
                definition?.let { YoutubeApiParameter.Option.Search.Definition.valueOf(it) },
                duration?.let { YoutubeApiParameter.Option.Search.Duration.valueOf(it) })
    }
}

data class YoutubeSearchCondition(
        val searchQuery: String? = null,
        val channelId: String? = null,
        val event: YoutubeApiParameter.Option.Search.Event? = null,
        val orderType: YoutubeApiParameter.Option.Search.OrderType? = null,
        val publishedAfter: Date? = null,
        val publishedBefore: Date? = null,
        val regionCode: YoutubeApiParameter.Option.RegionCode? = null,
        val searchTypes: Set<YoutubeApiParameter.Option.Search.SearchType>,
        val caption: YoutubeApiParameter.Option.Search.Caption? = null,
        val videoCategoryid: String? = null,
        val definition: YoutubeApiParameter.Option.Search.Definition? = null,
        val duration: YoutubeApiParameter.Option.Search.Duration? = null

): ConvertibleToRealmModel<YoutubeSearchConditionRealmModel> {

    override fun toRealmModel(): YoutubeSearchConditionRealmModel {
        var types = RealmList<String>()
        types.addAll(searchTypes.map { it.toString() })
        return YoutubeSearchConditionRealmModel(
                searchQuery,
                channelId,
                event?.toString(),
                orderType?.toString(),
                publishedAfter,
                publishedBefore,
                regionCode?.toString(),
                types,
                caption?.toString(),
                videoCategoryid,
                definition.toString(),
                duration.toString())
    }

    fun toYoutubeSearchOptionParameter(): Set<YoutubeApiParameter.Option.Search> {

        val searchQParam = if (searchQuery != null) YoutubeApiParameter.Option.Search.Q(searchQuery) else null
        val channelIdParam = if (channelId != null) YoutubeApiParameter.Option.Search.ChannelId(channelId) else null
        val eventTypeParam = if (event != null) YoutubeApiParameter.Option.Search.EventType(event) else null
        val orderParam = if (orderType != null) YoutubeApiParameter.Option.Search.Order(orderType) else null
        val publishedAfterParam = if (publishedAfter != null) YoutubeApiParameter.Option.Search.PublishedAfter(publishedAfter) else null
        val publishedBeforeParam = if (publishedBefore != null) YoutubeApiParameter.Option.Search.PublishedBefore(publishedBefore) else null
        val regionCodeParam = if (regionCode != null) YoutubeApiParameter.Option.Search.RegionCode(regionCode) else null
        val typeParam = if (searchTypes != null) YoutubeApiParameter.Option.Search.Type(searchTypes) else null
        val videoCaptionParam = if (caption != null) YoutubeApiParameter.Option.Search.VideoCaption(caption) else null
        val videoCategoryidParam = if (videoCategoryid != null) YoutubeApiParameter.Option.Search.VideoCategoryId(videoCategoryid) else null
        val videoDefinitionParam = if (definition != null) YoutubeApiParameter.Option.Search.VideoDefinition(definition) else null
        val videoDurationParam = if (duration != null) YoutubeApiParameter.Option.Search.VideoDuration(duration) else null


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