package com.example.hiroshi.rxtubeapp.ui.player

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchChannelDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchPlaylistDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeVideosRepository
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsAdapter
import io.reactivex.disposables.CompositeDisposable

class PlayerViewModel(private val videoRepository: YoutubeVideosRepository): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    // Output
    private val mutableRelatedVideos: MutableLiveData<List<SearchVideoDetail>> = MutableLiveData()
    val relatedVideosAdapter: LiveData<RelatedVideosAdapter> = Transformations.map(mutableRelatedVideos, { details ->
        val viewModels = details.map { RelatedVideoViewModel.create(it) }
        RelatedVideosAdapter(viewModels)
    })

    // Input

    fun videoDidTap(video: SearchVideoDetail) {

    }

    fun relatedVideoDidTapPosition(position: Int) {

    }

}