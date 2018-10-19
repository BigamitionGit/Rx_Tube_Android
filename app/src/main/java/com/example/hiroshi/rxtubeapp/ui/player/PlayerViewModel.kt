package com.example.hiroshi.rxtubeapp.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeRelatedVideosRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeVideosRateRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlayerViewModel(
        private val relatedVideosRepository: YoutubeRelatedVideosRepository,
        private val videosRateRepository: YoutubeVideosRateRepository): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    // Output
    private val mutableRelatedVideos: MutableLiveData<List<SearchVideoDetail>> = MutableLiveData()
    val relatedVideosAdapter: LiveData<RelatedVideosAdapter> = Transformations
            .map(mutableRelatedVideos, { details ->
                val viewModels = details.map { RelatedVideoViewModel.create(it) }
                RelatedVideosAdapter(viewModels)
            })

    private val mutablePlay: MutableLiveData<String> = MutableLiveData()
    val play: LiveData<String> = mutablePlay


    // Input

    fun videoDidTap(video: SearchVideoDetail) {

        mutablePlay.postValue(video.player.embedHtml)

        relatedVideosRepository.fetch(video.id)
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableRelatedVideos.postValue(it) })
                .addTo(compositeDisposable)
    }

    fun relatedVideoDidTapPosition(position: Int) {

        mutableRelatedVideos.value?.let {
            val playVideo = it[position]
            mutablePlay.postValue(playVideo.player.embedHtml)

            relatedVideosRepository.fetch(playVideo.id)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(onSuccess = { mutableRelatedVideos.postValue(it) })
                    .addTo(compositeDisposable)
        }
    }

}