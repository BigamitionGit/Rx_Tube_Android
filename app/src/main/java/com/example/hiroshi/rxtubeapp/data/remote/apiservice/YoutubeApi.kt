package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import com.example.hiroshi.rxtubeapp.extensions.beginWithLowerCase
import java.net.URL

/**
 * Created on 2018/03/12.
 */
sealed class YoutubeApi {
    data class Search(val youtubeApi: YoutubeApiParameter<
            YoutubeApiParameter.Require.Search,
            YoutubeApiParameter.Filter.Search,
            YoutubeApiParameter.Option.Search>): YoutubeApi()


    val parameters: Map<String, Any> by lazy {
        when (this) {
            is Search -> {
                this.youtubeApi.option
                        .map { it.parameter }
                        .reduce { result, element -> result.plus(element) }
                        .plus(this.youtubeApi.require.parameter)
                        .plus(this.youtubeApi.filter.parameter)
            }
        }
    }

    companion object {
        val baseURL: String = "https://www.googleapis.com"
    }



}

