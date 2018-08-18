package com.example.hiroshi.rxtubeapp.ui.searchitems

import com.example.hiroshi.rxtubeapp.data.remote.model.SearchChannelDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchPlaylistDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail

sealed class SearchItemViewModel {

    abstract fun getViewType(): ViewType

    enum class ViewType(val id: Int) {
        Video(10),
        Channel(20),
        Playlist(30);

        companion object {
            fun from(id: Int): ViewType {
                return values().first { it.id == id }
            }
        }

    }

    data class Video(val title: String) : SearchItemViewModel() {

        companion object Factory {
            fun create(video: SearchVideoDetail): Video {
                return Video(video.snippet.title)
            }
        }

        override fun getViewType(): ViewType {
            return ViewType.Video
        }

        fun onClickVideo() {

        }
    }

    data class Channel(val title: String) : SearchItemViewModel() {

        companion object Factory {
            fun create(channel: SearchChannelDetail): Channel {
                return Channel(channel.snippet.title)
            }
        }

        override fun getViewType(): ViewType {
            return ViewType.Channel
        }

        fun onClickChannel() {

        }
    }

    data class Playlist(val title: String) : SearchItemViewModel() {

        companion object Factory {
            fun create(playlist: SearchPlaylistDetail): Playlist {
                return Playlist(playlist.snippet.title)
            }
        }

        override fun getViewType(): ViewType {
            return ViewType.Playlist
        }

        fun onClickPlaylist() {

        }
    }
}