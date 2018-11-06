package com.example.hiroshi.rxtubeapp.data.remote.model

import com.squareup.moshi.Json
import se.ansman.kotshi.GetterName
import se.ansman.kotshi.JsonSerializable

/**
 * Created on 2018/03/12.
 */

@JsonSerializable
data class Thumbnails(
        @GetterName("getDefault")
        @Json(name = "default")
        val default: Image,
        val medium: Image,
        val high: Image,
        val standard: Image

) {

    @JsonSerializable
    data class Image(
            val url: String,
            val width: Int,
            val height: Int
    )
}