package com.example.hiroshi.rxtubeapp.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable
import java.util.*

@JsonSerializable
@Parcelize
data class Channels(val items: Array<Item>): Parcelable {

    @JsonSerializable
    @Parcelize
    data class Item(val id: String,
                    val snippet: Snippet?,
                    val contentDetails: ContentDetails?,
                    val statistics: Statistics?,
                    val topicDetails: TopicDetails?): Parcelable {

        @JsonSerializable
        @Parcelize
        data class Snippet(val title: String,
                           val description: String,
                           val publishedAt: String,
                           val thumbnails: Thumbnails): Parcelable

        @JsonSerializable
        @Parcelize
        data class ContentDetails(val relatedPlaylists: Array<RelatedPlaylists>): Parcelable {

            @JsonSerializable
            @Parcelize
            data class RelatedPlaylists(val likes: String,
                                        val favorites: String,
                                        val uploads: String,
                                        val watchHistory: String,
                                        val watchLater: String): Parcelable
        }

        @JsonSerializable
        @Parcelize
        data class Statistics(val viewCount: Int,
                              val commentCount: Int,
                              val subscriberCount: Int,
                              val hiddenSubscriberCount: Int,
                              val videoCount: Int): Parcelable

        @JsonSerializable
        @Parcelize
        data class TopicDetails(val topicIds: Array<String>): Parcelable
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Channels

        if (!Arrays.equals(items, other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(items)
    }
}