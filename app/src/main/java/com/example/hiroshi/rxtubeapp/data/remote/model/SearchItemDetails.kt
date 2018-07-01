package com.example.hiroshi.rxtubeapp.data.remote.model

data class SearchItemDetails(val items: Array<ItemType>) {

    sealed class ItemType {

        data class Video(val video: Videos.Item,
                         val channel: Channels.Item): ItemType()

        data class Channel(val channel: Channels.Item): ItemType()

        data class Playlist(val playlist: Playlists.Item,
                            val channel: Channels.Item): ItemType()
    }
}