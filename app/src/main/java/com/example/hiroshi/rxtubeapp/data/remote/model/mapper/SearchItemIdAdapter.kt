package com.example.hiroshi.rxtubeapp.data.remote.model.mapper

import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItemId
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.json.JSONException
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
class IdJson(
        val kind: String,
        val videoId: String?,
        val channelId: String?,
        val playlistId: String?
)

class SearchItemIdAdapter {

    @FromJson
    fun idFromJson(id: IdJson): SearchItemId {

        when {
            id.kind == SearchItemId.Kind.VIDEO.value && id.videoId != null -> {
                return SearchItemId(id.videoId, SearchItemId.Kind.VIDEO)
            }
            id.kind == SearchItemId.Kind.CHANNEL.value && id.channelId != null -> {
                return SearchItemId(id.channelId, SearchItemId.Kind.CHANNEL)
            }
            id.kind == SearchItemId.Kind.PLAYLIST.value && id.playlistId != null -> {
                return SearchItemId(id.playlistId, SearchItemId.Kind.PLAYLIST)
            }
            else -> throw JSONException("unknown ID kind: " + id.kind)
        }
    }

    @ToJson
    fun idToJson(id: SearchItemId): IdJson {

        when (id.kind) {
            SearchItemId.Kind.VIDEO -> return IdJson(id.kind.value, id.id, null, null)
            SearchItemId.Kind.CHANNEL -> return IdJson(id.kind.value, null, id.id, null)
            SearchItemId.Kind.PLAYLIST -> return IdJson(id.kind.value, null, null, id.id)
        }

    }
}