package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.*
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Single
import io.reactivex.functions.BiFunction

interface YoutubeRelatedVideosRepository {

    fun fetch(videoId: String): Single<List<SearchVideoDetail>>
}

class YoutubeRelatedVideosRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeRelatedVideosRepository {

    override fun fetch(videoId: String): Single<List<SearchVideoDetail>> {

        val searchParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Search(setOf(SearchRequire.ID, SearchRequire.SNIPPET)),
                YoutubeApiParameter.Filter.Search.RelatedToVideoId(videoId),
                setOf())
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = searchParameter.parameters))
                .flatMap { searchItems ->
                    val videoIds = searchItems.items.map { it.id.id }
                    val channelIds = searchItems.items.map { it.snippet.channelId }

                    val videoParameter = YoutubeApiParameter(
                            YoutubeApiParameter.Require.Videos(setOf(VideosRequire.SNIPPET, VideosRequire.PLAYER, VideosRequire.STATISTICS)),
                            YoutubeApiParameter.Filter.Videos.Id(videoIds),
                            setOf())

                    val channelParameter = YoutubeApiParameter(
                            YoutubeApiParameter.Require.Channels(setOf(ChannelsRequire.SNIPPET, ChannelsRequire.CONTENTDETAILS, ChannelsRequire.STATISTICS)),
                            YoutubeApiParameter.Filter.Channels.Id(channelIds),
                            setOf())

                    Single
                            .zip(
                                    youtubeApiService.videos(videoParameter.parameters),
                                    youtubeApiService.channels(channelParameter.parameters),
                                    BiFunction { videos: Videos, channels: Channels ->

                                        fun toDetails(item: SearchItems.Item): SearchVideoDetail? {

                                            val v = videos.items.firstOrNull { it.id == item.id.id } ?: return null
                                            val c = channels.items.firstOrNull { it.id == item.snippet.channelId } ?: return null
                                            return SearchVideoDetail.create(v, c)
                                        }

                                        searchItems.items.mapNotNull { toDetails(it) }
                                    })
                }
    }
}