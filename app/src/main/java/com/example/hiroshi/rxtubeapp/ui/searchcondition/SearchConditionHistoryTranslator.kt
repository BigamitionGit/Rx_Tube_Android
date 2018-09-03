package com.example.hiroshi.rxtubeapp.ui.searchcondition

/**
 * Created by hosodahiroshi on 2018/09/03.
 */

import android.content.Context
import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter

class SearchConditionHistoryTranslator(private val context: Context) {

    fun translate(condition: YoutubeSearchCondition): List<String> {
        val query = condition.searchQuery
        val searchType = condition.searchTypes?.let { types->String
            types.mapNotNull {type->String
                when (type) {
                    YoutubeApiParameter.Option.Search.SearchType.VIDEO -> context.getString(R.string.condition_search_type_video)
                    YoutubeApiParameter.Option.Search.SearchType.CHANNEL -> context.getString(R.string.condition_search_type_channel)
                    YoutubeApiParameter.Option.Search.SearchType.PLAYLIST -> context.getString(R.string.condition_search_type_playlist)
                }
            }.joinToString(separator = ",")
        }

        return listOfNotNull(
                query,
                searchType
        )
    }
}