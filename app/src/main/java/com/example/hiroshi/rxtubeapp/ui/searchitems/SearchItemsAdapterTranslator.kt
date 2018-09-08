package com.example.hiroshi.rxtubeapp.ui.searchitems

import com.example.hiroshi.rxtubeapp.data.remote.model.SearchChannelDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItemDetails
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchPlaylistDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail

class SearchItemsAdapterTranslator {

    fun translate(details: SearchItemDetails): SearchItemsAdapter {
        val viewModels = details.items.map { item ->
            when (item) {
                is SearchVideoDetail -> SearchItemViewModel.Video.create(item)
                is SearchChannelDetail -> SearchItemViewModel.Channel.create(item)
                is SearchPlaylistDetail -> SearchItemViewModel.Playlist.create(item)
            }
        }
        return SearchItemsAdapter(viewModels)
    }
}