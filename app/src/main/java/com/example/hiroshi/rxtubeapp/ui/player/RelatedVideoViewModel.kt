package com.example.hiroshi.rxtubeapp.ui.player

import androidx.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail

interface RelatedVideoViewModelType {

    fun onClickChannel()
}

data class RelatedVideoViewModel(val title: String): ViewModel(), RelatedVideoViewModelType {

    companion object Factory {
        fun create(video: SearchVideoDetail): RelatedVideoViewModel {
            return RelatedVideoViewModel(video.snippet.title)
        }
    }

    override fun onClickChannel() {

    }
}