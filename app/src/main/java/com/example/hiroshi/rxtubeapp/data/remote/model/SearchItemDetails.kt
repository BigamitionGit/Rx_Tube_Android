package com.example.hiroshi.rxtubeapp.data.remote.model

import java.nio.channels.Channel

data class SearchItemDetails(val items: List<SearchItemDetailType>) {

}

sealed class SearchItemDetailType
data class SearchVideoDetail(val id: String,
                             val snippet: Videos.Item.Snippet,
                             val contentDetails: Videos.Item.ContentDetails,
                             val statistics: Videos.Item.Statistics,
                             val player: Videos.Item.Player,
                             val channelId: String,
                             val channelSnippet: Channels.Item.Snippet,
                             val channelContentDetails: Channels.Item.ContentDetails,
                             val channelStatistics: Channels.Item.Statistics,
                             val channelTopicDetails: Channels.Item.TopicDetails): SearchItemDetailType() {

    companion object Factory {
        fun create(video: Videos.Item, channel: Channels.Item): SearchVideoDetail? {
            val snippet = video.snippet ?: return null
            val contentDetails = video.contentDetails ?: return null
            val statistics = video.statistics ?: return null
            val player = video.player ?: return null
            val channelSnippet = channel.snippet ?: return null
            val channelContentDetails = channel.contentDetails ?: return null
            val channelStatistics = channel.statistics ?: return null
            val channelTopicDetails = channel.topicDetails ?: return null

            return SearchVideoDetail(
                    video.id,
                    snippet,
                    contentDetails,
                    statistics,
                    player,
                    channel.id,
                    channelSnippet,
                    channelContentDetails,
                    channelStatistics,
                    channelTopicDetails)
        }
    }
}

data class SearchChannelDetail(val id: String,
                               val snippet: Channels.Item.Snippet,
                               val contentDetails: Channels.Item.ContentDetails,
                               val statistics: Channels.Item.Statistics,
                               val topicDetails: Channels.Item.TopicDetails): SearchItemDetailType() {

    companion object Factory {
        fun create(channel: Channels.Item): SearchChannelDetail? {
            val snippet = channel.snippet ?: return null
            val contentDetails = channel.contentDetails ?: return null
            val statistics = channel.statistics ?: return null
            val topicDetails = channel.topicDetails ?: return null

            return SearchChannelDetail(
                    channel.id,
                    snippet,
                    contentDetails,
                    statistics,
                    topicDetails)
        }
    }
}

data class SearchPlaylistDetail(val id: String,
                                val snippet: Playlists.Item.Snippet,
                                val contentDetails: Playlists.Item.ContentDetails,
                                val player: Playlists.Item.Player,
                                val channelId: String,
                                val channelSnippet: Channels.Item.Snippet,
                                val channelContentDetails: Channels.Item.ContentDetails,
                                val channelStatistics: Channels.Item.Statistics,
                                val channelTopicDetails: Channels.Item.TopicDetails): SearchItemDetailType() {

    companion object Factory {
        fun create(playlist: Playlists.Item, channel: Channels.Item): SearchPlaylistDetail? {
            val snippet = playlist.snippet ?: return null
            val contentDetails = playlist.contentDetails ?: return null
            val player = playlist.player ?: return null
            val channelSnippet = channel.snippet ?: return null
            val channelContentDetails = channel.contentDetails ?: return null
            val channelStatistics = channel.statistics ?: return null
            val channelTopicDetails = channel.topicDetails ?: return null

            return SearchPlaylistDetail(
                    playlist.id,
                    snippet,
                    contentDetails,
                    player,
                    channel.id,
                    channelSnippet,
                    channelContentDetails,
                    channelStatistics,
                    channelTopicDetails)
        }
    }
}