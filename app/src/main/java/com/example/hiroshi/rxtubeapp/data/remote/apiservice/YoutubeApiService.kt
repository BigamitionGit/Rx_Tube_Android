package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*

/**
 * Created on 2018/03/12.
 */


interface YoutubeApiService {

    @GET("/youtube/v3/search")
    fun search(@QueryMap parameter: Map<String, Any>): Single<SearchItems>
}