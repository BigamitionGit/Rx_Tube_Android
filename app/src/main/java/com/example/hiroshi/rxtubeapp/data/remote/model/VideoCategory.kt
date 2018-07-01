package com.example.hiroshi.rxtubeapp.data.remote.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class VideoCategory(val id: String,
                         val snippet: Snippet) {

    @JsonSerializable
    data class Snippet(val channelId: String,
                       val title: String,
                       val assignable: Boolean)


}