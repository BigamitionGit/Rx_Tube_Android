package com.example.hiroshi.rxtubeapp.data.remote.model

import se.ansman.kotshi.JsonSerializable

/**
 * Created  on 2018/03/12.
 */

@JsonSerializable
data class SearchItems(
        val items: List<Item>
) {
    @JsonSerializable
    data class Item(
            val id: SearchItemId,
            val snippet: Snippet
    ) {

        @JsonSerializable
        data class Snippet(
                val publishedAt: String,
                val channelId: String,
                val title: String,
                val description: String,
                val thumbnails: Thumbnails,
                val channelTitle: String,
                val liveBroadcastContent: String
        )
    }
}

@JsonSerializable
data class SearchItemId(val id: String, val kind: Kind) {

    enum class Kind(val value: String) {
        VIDEO("video"),
        CHANNEL("channel"),
        PLAYLIST("playlist")
    }
}
