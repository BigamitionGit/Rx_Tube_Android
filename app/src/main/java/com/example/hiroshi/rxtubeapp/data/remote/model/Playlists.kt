package com.example.hiroshi.rxtubeapp.data.remote.model

import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class Playlists(val items: Array<Item>) {

    @JsonSerializable
    data class Item(val id: String,
                    val snippet: Snippet?,
                    val contentDetails: ContentDetails?,
                    val player: Player?) {

        @JsonSerializable
        data class Snippet(val title: String,
                           val description: String,
                           val publishedAt: String,
                           val thumbnails: Thumbnails,
                           val channelId: String,
                           val tags: Array<String>,
                           val channelTitle: String)

        @JsonSerializable
        data class ContentDetails(val itemCount: Int)

        @JsonSerializable
        data class Player(val embedhtml: String)
    }
}