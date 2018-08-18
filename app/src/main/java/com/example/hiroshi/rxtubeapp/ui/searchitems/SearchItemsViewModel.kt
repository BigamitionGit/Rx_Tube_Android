package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.arch.lifecycle.*
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchDetailRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SearchItemsViewModel(
        private val searchRepository: YoutubeSearchRepository,
        private  val searchDetailRepository: YoutubeSearchDetailRepository,
        private val searchConditionRepository: YoutubeSearchConditionRepository
): ViewModel()  {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // Output
    private val mutableSearchItemDetails: MutableLiveData<SearchItemDetails> = MutableLiveData()
    val searchItemAdapter: LiveData<SearchItemsAdapter> = Transformations.map(mutableSearchItemDetails, {details ->
        val viewModels = details.items.map { item ->
            when (item) {
                is SearchVideoDetail -> SearchItemViewModel.Video.create(item)
                is SearchChannelDetail -> SearchItemViewModel.Channel.create(item)
                is SearchPlaylistDetail -> SearchItemViewModel.Playlist.create(item)
            }
        }
        SearchItemsAdapter(viewModels)
    })


    // Input

    fun activityOnCreate() {

        searchConditionRepository.fetchSearchConditionHistory()
                .flatMapSingle { searchRepository.fetch(it.history.first().toYoutubeSearchOptionParameter()) }
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.value = it })
                .addTo(compositeDisposable)
    }

    fun search(condition: YoutubeSearchCondition) {

        searchRepository.fetch(condition.toYoutubeSearchOptionParameter())
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.value = it })
                .addTo(compositeDisposable)
    }

    fun refresh() {

        searchConditionRepository.fetchSearchConditionHistory()
                .flatMapSingle { searchRepository.fetch(it.history.first().toYoutubeSearchOptionParameter()) }
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.value = it })
                .addTo(compositeDisposable)
    }


}