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

        val videoIds = ids.filter { it.first.kind == SearchItemId.Kind.VIDEO }.map { it.first.id }
        val channelIds = ids.map { it.second }
        val playlistIds = ids.filter { it.first.kind == SearchItemId.Kind.PLAYLIST }.map { it.first.id }

        val videoParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Videos(setOf(VideosRequire.SNIPPET, VideosRequire.PLAYER, VideosRequire.STATISTICS)),
                YoutubeApiParameter.Filter.Videos.Id(videoIds),
                setOf())

        val channelParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Channels(setOf(ChannelsRequire.SNIPPET, ChannelsRequire.CONTENTDETAILS, ChannelsRequire.STATISTICS)),
                YoutubeApiParameter.Filter.Channels.Id(channelIds),
                setOf())

        val playlistParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Playlists(setOf(PlaylistsRequire.SNIPPET, PlaylistsRequire.CONTENTDETAILS, PlaylistsRequire.PLAYER)),
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
                                            SearchItemId.Kind.VIDEO -> {
                                                val v = videos.items.firstOrNull { it.id == id.first.id } ?: return null
                                                val c = channels.items.firstOrNull { it.id == id.second } ?: return null
                                                return SearchVideoDetail.create(v, c)
                                            }
                                            SearchItemId.Kind.CHANNEL -> {
                                                val c = channels.items.firstOrNull { it.id == id.first.id } ?: return null
                                                return SearchChannelDetail.create(c)
                                            }

                                            SearchItemId.Kind.PLAYLIST -> {
                                                val p = playlists.items.firstOrNull { it.id == id.first.id } ?: return null
                                                val c = channels.items.firstOrNull { it.id == id.second } ?: return null
                                                return SearchPlaylistDetail.create(p, c)
                                            }
                                        }
                                    }

                                    SearchItemDetails(ids.mapNotNull { toDetails(it) })
                                }))
    }
}
