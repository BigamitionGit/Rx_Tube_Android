package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import com.example.hiroshi.rxtubeapp.extensions.beginWithLowerCase
import java.net.URL

/**
 * Created on 2018/03/12.
 */
sealed class YoutubeApi {
    data class Search(val parameter: YoutubeApiParameter<
            YoutubeApiParameter.Require.Search,
            YoutubeApiParameter.Filter.Search,
            YoutubeApiParameter.Option.Search>): YoutubeApi()


    companion object {
        val baseURL: String = "https://www.googleapis.com"
    }



}

