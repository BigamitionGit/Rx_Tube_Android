package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.VideosRating
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import io.reactivex.Completable

interface YoutubeVideosRateRepository {

    fun like(videoId: String): Completable
    fun dislike(videoId: String): Completable
    fun none(videoId: String): Completable
}

class YoutubeVideosRateRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeVideosRateRepository {

    override fun like(videoId: String): Completable {
        val youtubeParameter = YoutubeApiParameter(YoutubeApiParameter.Require.VideosRate(videoId, VideosRating.like), null, setOf())
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.videosRate(youtubeParameter.parameters))
    }

    override fun dislike(videoId: String): Completable {
        val youtubeParameter = YoutubeApiParameter(YoutubeApiParameter.Require.VideosRate(videoId, VideosRating.dislike), null, setOf())
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.videosRate(youtubeParameter.parameters))
    }

    override fun none(videoId: String): Completable {
        val youtubeParameter = YoutubeApiParameter(YoutubeApiParameter.Require.VideosRate(videoId, VideosRating.none), null, setOf())
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.videosRate(youtubeParameter.parameters))
    }
}