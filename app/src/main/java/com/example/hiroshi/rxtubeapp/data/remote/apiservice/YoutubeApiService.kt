package com.example.hiroshi.rxtubeapp.data.remote.apiservice

import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*

/**
 * Created on 2018/03/12.
 */


interface YoutubeApiService {

    @GET("/youtube/v3/search")
    fun search(@QueryMap parameter: Map<String, Any>): Single<SearchItems>

    @GET("/youtube/v3/videos")
    fun videos(@QueryMap parameter: Map<String, Any>): Single<Videos>

    @POST("/youtube/v3/videos/rate")
    fun videosRate(@QueryMap parameter: Map<String, Any>): Completable

    @GET("youtube/v3/channels")
    fun channels(@QueryMap parameter: Map<String, Any>): Single<Channels>

    @GET("youtube/v3/playlists")
    fun playlists(@QueryMap parameter: Map<String, Any>): Single<Playlists>

    @GET("youtube/v3/videoCategories")
    fun videoCategories(@QueryMap parameter: Map<String, Any>): Single<VideoCategory>
}