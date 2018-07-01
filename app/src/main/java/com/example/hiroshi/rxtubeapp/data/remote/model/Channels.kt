package com.example.hiroshi.rxtubeapp.data.remote.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Channels(val items: Array<Item>) {

    @JsonSerializable
    data class Item(val id: String,
                    val snippet: Snippet?,
                    val contentDetails: ContentDetails?,
                    val statistics: Statistics?,
                    val topicDetails: TopicDetails?) {

        @JsonSerializable
        data class Snippet(val title: String,
                           val description: String,
                           val publishedAt: String,
                           val thumbnails: Thumbnails)

        @JsonSerializable
        data class ContentDetails(val relatedPlaylists: Array<RelatedPlaylists>) {

            @JsonSerializable
            data class RelatedPlaylists(val likes: String,
                                        val favorites: String,
                                        val uploads: String,
                                        val watchHistory: String,
                                        val watchLater: String)
        }

        @JsonSerializable
        data class Statistics(val viewCount: Int,
                              val commentCount: Int,
                              val subscriberCount: Int,
                              val hiddenSubscriberCount: Int,
                              val videoCount: Int)

        @JsonSerializable
        data class TopicDetails(val topicIds: Array<String>)
    }
}