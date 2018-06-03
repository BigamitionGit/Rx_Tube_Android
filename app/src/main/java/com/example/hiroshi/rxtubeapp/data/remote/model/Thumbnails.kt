package com.example.hiroshi.rxtubeapp.data.remote.model

/**
 * Created on 2018/03/12.
 */

data class Thumbnails(
        val default: Image,
        val medium: Image,
        val high: Image,
        val standard: Image

) {
    data class Image(
            val url: String,
            val width: Int,
            val height: Int
    )
}