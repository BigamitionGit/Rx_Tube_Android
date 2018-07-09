package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.toSingle

/**
 * Created on 2018/03/14.
 */

private typealias SearchRequire = YoutubeApiParameter.Require.Search.Property
private typealias VideosRequire = YoutubeApiParameter.Require.Videos.Property
private typealias ChannelsRequire = YoutubeApiParameter.Require.Channels.Property
private typealias PlaylistsRequire = YoutubeApiParameter.Require.Playlists.Property

interface YoutubeSearchRepository {

    fun fetchSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems>

    fun fetchSearchItemDetails(ids: List<Pair<SearchItemId, String>>): Single<SearchItemDetails>
}

class YoutubeSearchRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeSearchRepository {

    override fun fetchSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems> {
        val searchParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Search(setOf(SearchRequire.id, SearchRequire.snippet)),
                null,
                options)
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = searchParameter.parameters))
    }

    override fun fetchSearchItemDetails(ids: List<Pair<SearchItemId, String>>): Single<SearchItemDetails> {

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

        return Single
                .zip(
                        youtubeApiService.videos(videoParameter.parameters),
                        youtubeApiService.channels(channelParameter.parameters),
                        youtubeApiService.playlists(playlistParameter.parameters),
                        Function3 { videos: Videos, channels: Channels, playlists: Playlists ->

                            fun toDetails(id: Pair<SearchItemId, String>):SearchItemDetails.ItemType? {

                                when (id.first.kind) {
                                    SearchItemId.Kind.video -> {
                                        val v = videos.items.first { it.id == id.first.id } ?: return null
                                        val c = channels.items.first { it.id == id.second } ?: return null
                                        return SearchItemDetails.ItemType.Video(v, c)
                                    }
                                    SearchItemId.Kind.channel -> {
                                        val c = channels.items.first { it.id == id.first.id } ?: return null
                                        return SearchItemDetails.ItemType.Channel(c)
                                    }
                                    SearchItemId.Kind.playlist -> {
                                        val p = playlists.items.first { it.id == id.first.id } ?: return null
                                        val c = channels.items.first { it.id == id.second } ?: return null
                                        return SearchItemDetails.ItemType.Playlist(p, c)
                                    }
                                }
                            }

                            SearchItemDetails(ids.mapNotNull { toDetails(it) })
                        })
    }


}