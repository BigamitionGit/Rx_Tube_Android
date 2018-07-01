package com.example.hiroshi.rxtubeapp.data.remote.model

import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Videos(val items: Array<Item>) {

    @JsonSerializable
    data class Item(val id: String,
                    val snippet: Snippet?,
                    val contentDetails: ContentDetails?,
                    val statistics: Statistics?,
                    val player: Player?,
                    val topicDetails: TopicDetails?,
                    val recordingDetails: RecordingDetails?) {

        @JsonSerializable
        data class Snippet(val publishedAt: String,
                           val channelId: String,
                           val title: String,
                           val description: String,
                           val thumbnails: Thumbnails,
                           val channelTitle: String,
                           val tags: Array<String>,
                           val categoryId: String)

        @JsonSerializable
        data class ContentDetails(val duration: String,
                                  val dimension: String,
                                  val definition: String,
                                  val caption: String)

        @JsonSerializable
        data class Statistics(val viewCount: Int,
                              val likeCount: Int,
                              val dislikeCount: Int,
                              val favoriteCount: Int,
                              val commentCount: Int)

        @JsonSerializable
        data class Player(val embedHtml: String)

        @JsonSerializable
        data class TopicDetails(val relevantTopicIds: Array<String>,
                                val topicCategories: Array<String>)

        @JsonSerializable
        data class RecordingDetails(val locationDescription: String,
                                    val location: Location,
                                    val recordingDate: String) {

            @JsonSerializable
            data class Location(val latitude: Double,
                                val longitude: Double,
                                val altitude: Double)
        }
    }
}