package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import java.util.*

typealias SearchRequire = YoutubeApiParameter.Require.Search.Property
typealias VideosRequire = YoutubeApiParameter.Require.Videos.Property
typealias ChannelsRequire = YoutubeApiParameter.Require.Channels.Property
typealias PlaylistsRequire = YoutubeApiParameter.Require.Playlists.Property

class YoutubeApiParameter<
        R: YoutubeApiParameter.Require,
        F: YoutubeApiParameter.Filter,
        O: YoutubeApiParameter.Option>(
        private val require: R,
        private val filter: F?,
        private val option: Set<O>) {

    val parameters: Map<String, Any> by lazy {
        this.option
                .map { it.parameter }
                .reduce { result, element -> result.plus(element) }
                .plus(this.require.parameter)
                .plus(if (this.filter != null) this.filter.parameter else mapOf())
    }

    interface Require {
        val parameter: Map<String, Any>

        data class Search(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ","))
            }
        }

        data class Videos(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id,
                contentDetails,
                player,
                recordingDetails,
                statistics,
                topicDetails
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ","))
            }
        }

        data class Channels(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id,
                contentDetails,
                statistics,
                topicDetails
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ",") )
            }
        }

        data class Playlists(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id,
                contentDetails,
                player
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ","))
            }
        }

        data class VideoCategory(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ","))
            }
        }

        data class Subscriptions(private val properties: Set<Property>): Require {
            enum class Property {
                snippet,
                id,
                contentDetails
            }

            override val parameter: Map<String, Any> by lazy {
                mapOf<String, Any>("part" to properties.joinToString(separator = ","))
            }
        }
    }

    interface Filter {
        val parameter: Map<String, Any>

        sealed class Search: Filter {
            data class ForContentOwner(val forContentOwner: Boolean): Search()
            data class ForMine(val forMine: Boolean): Search()
            data class RelatedToVideoId(val id: String): Search()


            override val parameter: Map<String, Any> by lazy {
                when (this) {
                    is ForContentOwner -> mapOf("forContentOwner" to this.forContentOwner)
                    is ForMine -> mapOf("forMine" to this.forMine)
                    is RelatedToVideoId -> mapOf("relatedToVideoId" to this.id)
                }
            }
        }

        sealed class Videos: Filter {
            class Chart(): Videos()
            data class Id(val ids: List<String>): Videos()
            data class MyRating(val rating: Rating): Videos()

            override val parameter: Map<String, Any> by lazy {
                when (this) {
                    is Chart -> mapOf("chart" to "mostPopular")
                    is Id -> mapOf("id" to this.ids.joinToString(separator = ","))
                    is MyRating -> mapOf("myRating" to this.rating.toString())
                }
            }

            enum class Rating {
                dislike,
                like
            }
        }

        sealed class Channels: Filter {
            data class CategoryId(val id: String): Channels()
            data class ForUsername(val name: String): Channels()
            data class Id(val ids: List<String>): Channels()
            class Mine(): Channels()

            override val parameter: Map<String, Any> by lazy {
                when (this) {
                    is CategoryId -> mapOf("categoryId" to this.id)
                    is ForUsername -> mapOf("forUsername" to this.name)
                    is Id -> mapOf("id" to this.ids.joinToString(separator = ","))
                    is Mine -> mapOf("mine" to true)
                }
            }
        }

        sealed class Playlists: Filter {
            data class ChannelId(val id: String): Playlists()
            data class Id(val ids: List<String>): Playlists()
            class Mine: Playlists()

            override val parameter: Map<String, Any> by lazy {
                when (this) {
                    is ChannelId -> mapOf("channelid" to this.id)
                    is Id -> mapOf("id" to this.ids.joinToString(separator = ","))
                    is Mine -> mapOf("mine" to true)
                }
            }
        }
    }

    interface Option {
        val parameter: Map<String, Any>

        enum class RegionCode(val value: String, val parameter: String) {
            JP("JP", "regionCode"),
            US("US", "regionCode"),
            CN("CN", "regionCode"),
            FR("FR", "regionCode"),
            GB("GB", "regionCode"),
            IN("IN", "regionCode"),
            KR("KR", "regionCode"),
            TW("TW", "regionCode"),
            CA("CA", "regionCode"),
            HK("HK", "regionCode"),
            RU("RU", "regionCode")
        }

        sealed class Search: Option {
            data class ChannelId(val id: String): Search()
            data class EventType(val event: Event): Search()
            data class MaxResults(val max: Int): Search()
            data class Order(val order: OrderType): Search()
            data class PublishedAfter(val time: Date): Search()
            data class PublishedBefore(val time: Date): Search()
            data class Q(val keyword: String): Search()
            data class RegionCode(val code: RegionCode): Search()
            data class Type(val type: SearchType): Search()
            data class VideoCaption(val caption: Caption): Search()
            data class VideoCategoryId(val id: String): Search()
            data class VideoDefinition(val definition: Definition): Search()
            data class VideoDuration(val duration: Duration): Search()

            override val parameter: Map<String, Any> by lazy {
                when (this) {
                    is ChannelId -> mapOf("channelId" to this.id)
                    is EventType -> mapOf("eventType" to this.event)
                    is MaxResults -> mapOf("maxResults" to this.max)
                    is Order -> mapOf("order" to this.order)
                    is PublishedAfter -> mapOf("publishedAfter" to this.time)
                    is PublishedBefore -> mapOf("publishedBefore" to this.time)
                    is Q -> mapOf("q" to this.keyword)
                    is RegionCode -> mapOf("regionCode" to this.code)
                    is Type -> mapOf("type" to this.type)
                    is VideoCaption -> mapOf("videoCaption" to this.caption)
                    is VideoCategoryId -> mapOf("videoCategoryId" to this.id)
                    is VideoDefinition -> mapOf("videoDefinition" to this.definition)
                    is VideoDuration -> mapOf("videoDuration" to this.duration)
                }
            }

            override fun equals(other: Any?): Boolean {
                return when (this) {
                    is ChannelId -> other is ChannelId
                    is EventType -> other is EventType
                    is MaxResults -> other is MaxResults
                    is Order -> other is Order
                    is PublishedAfter -> other is PublishedAfter
                    is PublishedBefore -> other is PublishedBefore
                    is Q -> other is Q
                    is RegionCode -> other is RegionCode
                    is Type -> other is Type
                    is VideoCaption -> other is VideoCaption
                    is VideoCategoryId -> other is VideoCategoryId
                    is VideoDefinition -> other is VideoDefinition
                    is VideoDuration -> other is VideoDuration
                }
            }

            enum class Event(val value: String) {
                COMPLETED("completed"),
                LIVE("live"),
                UPCOMING("upcoming")
            }

            enum class OrderType(val value: String) {
                DATE("date"),
                RATING("rating"),
                RELEVANCE("relevance"),
                TITLE("title"),
                VIDEOCOUNT("videoCount"),
                VIEWCOUNT("viewCount")
            }

            enum class SearchType(val value: String) {
                CHANNEL("channel"),
                PLAYLIST("playlist"),
                VIDEO("video")
            }

            enum class Caption(val value: String) {
                ANY("any"),
                CLOSEDCAPTION("closedCaption"),
                NONE("none")
            }

            enum class Definition(val value: String) {
                ANY("any"),
                HIGH("high"),
                STANDARD("standard")
            }

            enum class Duration(val value: String) {
                ANY("any"),
                LONG("long"),
                MEDIUM("medium"),
                SHORT("short")
            }

        }

    }
}