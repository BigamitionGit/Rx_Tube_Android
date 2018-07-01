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
            id.kind == SearchItemId.Kind.video.value && id.videoId != null -> {
                return SearchItemId(id.videoId, SearchItemId.Kind.video)
            }
            id.kind == SearchItemId.Kind.channel.value && id.channelId != null -> {
                return SearchItemId(id.channelId, SearchItemId.Kind.channel)
            }
            id.kind == SearchItemId.Kind.playlist.value && id.playlistId != null -> {
                return SearchItemId(id.playlistId, SearchItemId.Kind.playlist)
            }
            else -> throw JSONException("unknown id kind: " + id.kind)
        }
    }

    @ToJson
    fun idToJson(id: SearchItemId): IdJson {

        when (id.kind) {
            SearchItemId.Kind.video -> return IdJson(id.kind.value, id.id, null, null)
            SearchItemId.Kind.channel -> return IdJson(id.kind.value, null, id.id, null)
            SearchItemId.Kind.playlist -> return IdJson(id.kind.value, null, null, id.id)
        }

    }
}