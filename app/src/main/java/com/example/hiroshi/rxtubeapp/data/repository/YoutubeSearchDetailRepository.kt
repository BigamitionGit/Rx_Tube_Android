package com.example.hiroshi.rxtubeapp.data.repository

import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.*
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Single
import io.reactivex.functions.Function3

interface YoutubeSearchDetailRepository {

    fun fetch(ids: List<Pair<SearchItemId, String>>): Single<SearchItemDetails>
}

class YoutubeSearchDetailRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeSearchDetailRepository {

    override fun fetch(ids: List<Pair<SearchItemId, String>>): Single<SearchItemDetails> {

        val videoIds = ids.filter { it.first.kind == SearchItemId.Kind.video }.map { it.first.id }
        val channelIds = ids.map { it.second }
        val playlistIds = ids.filter { it.first.kind == SearchItemId.Kind.playlist }.map { it.first.id }

        val videoParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Videos(setOf(VideosRequire.snippet, VideosRequire.player, VideosRequire.statistics)),
                YoutubeApiParameter.Filter.Videos.Id(videoIds),
                setOf())

        val channelParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Channels(setOf(ChannelsRequire.snippet, ChannelsRequire.contentDetails, ChannelsRequire.statistics)),
                YoutubeApiParameter.Filter.Channels.Id(channelIds),
                setOf())

        val playlistParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Playlists(setOf(PlaylistsRequire.snippet, PlaylistsRequire.contentDetails, PlaylistsRequire.player)),
                YoutubeApiParameter.Filter.Playlists.Id(playlistIds),
                setOf())

        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(Single
                        .zip(
                                youtubeApiService.videos(videoParameter.parameters),
                                youtubeApiService.channels(channelParameter.parameters),
                                youtubeApiService.playlists(playlistParameter.parameters),
                                Function3 { videos: Videos, channels: Channels, playlists: Playlists ->

                                    fun toDetails(id: Pair<SearchItemId, String>): SearchItemDetailType? {

                                        when (id.first.kind) {
                                            SearchItemId.Kind.video -> {
                                                val v = videos.items.first { it.id == id.first.id } ?: return null
                                                val c = channels.items.first { it.id == id.second } ?: return null
                                                return SearchVideoDetail.create(v, c)
                                            }
                                            SearchItemId.Kind.channel -> {
                                                val c = channels.items.first { it.id == id.first.id } ?: return null
                                                return SearchChannelDetail.create(c)
                                            }

                                            SearchItemId.Kind.playlist -> {
                                                val p = playlists.items.first { it.id == id.first.id } ?: return null
                                                val c = channels.items.first { it.id == id.second } ?: return null
                                                return SearchPlaylistDetail.create(p, c)
                                            }
                                        }
                                    }

                                    SearchItemDetails(ids.mapNotNull { toDetails(it) })
                                }))
    }
}
