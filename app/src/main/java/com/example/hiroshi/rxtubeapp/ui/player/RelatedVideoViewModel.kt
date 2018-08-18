package com.example.hiroshi.rxtubeapp.ui.player

import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail

data class RelatedVideoViewModel(val title: String) {

    companion object Factory {
        fun create(video: SearchVideoDetail): RelatedVideoViewModel {
            return RelatedVideoViewModel(video.snippet.title)
        }
    }

    fun onClickPlaylist() {

    }
}