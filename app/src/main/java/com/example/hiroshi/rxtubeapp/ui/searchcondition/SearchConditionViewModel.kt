package com.example.hiroshi.rxtubeapp.ui.searchcondition

import android.arch.lifecycle.*
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SearchConditionViewModel(
        private val searchConditionRepository: YoutubeSearchConditionRepository,
        private val searchConditionHistoryTranslator: SearchConditionHistoryTranslator
): ViewModel(), LifecycleObserver {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // Q
    val searchText: MutableLiveData<String> = MutableLiveData()

    // SearchType
    val videoTypeText = MutableLiveData<String>()
    val channelTypeText = MutableLiveData<String>()
    val playlistTypeText = MutableLiveData<String>()
    val isVideoTypeSelected = MutableLiveData<Boolean>()
    val isChannelTypeSelected = MutableLiveData<Boolean>()
    val isPlaylistTypeSelected = MutableLiveData<Boolean>()

    // History
    val mutableSearchHistory = MutableLiveData<List<YoutubeSearchCondition>>()
    val searchHistory: LiveData<List<List<String>>> = Transformations.map(mutableSearchHistory, { searchConditionHistoryTranslator.translate(it) })

    // Search
    private val mutableSearch = MutableLiveData<YoutubeSearchCondition>()
    val search: LiveData<YoutubeSearchCondition> = mutableSearch


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        searchConditionRepository.fetchSearchConditionHistory()
                .map { it.history }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchHistory.postValue(it) })
                .addTo(compositeDisposable)
    }

    fun tapVideoType() {
        isVideoTypeSelected.value?.let { isVideoTypeSelected.postValue(!it) }
    }

    fun tapChannelType() {
        isChannelTypeSelected.value?.let { isChannelTypeSelected.postValue(!it) }
    }

    fun tapPlaylistType() {
        isPlaylistTypeSelected.value?.let { isPlaylistTypeSelected.postValue(!it) }
    }

    fun tapHistory(position: Int) {
        val history = mutableSearchHistory.value?.let { it[position] }
        if (history != null) {
            mutableSearch.postValue(history)
        }
    }

    fun tapSearch() {
        val videoType = isVideoTypeSelected.value?.let { if (it) YoutubeApiParameter.Option.Search.SearchType.VIDEO else null }
        val channelType = isChannelTypeSelected.value?.let { if (it) YoutubeApiParameter.Option.Search.SearchType.CHANNEL else null }
        val playlistType = isPlaylistTypeSelected.value?.let { if (it) YoutubeApiParameter.Option.Search.SearchType.PLAYLIST else null }
        val types = listOfNotNull(videoType, channelType, playlistType).toSet()
        val text = searchText.value

        val condition = YoutubeSearchCondition(searchTypes = types, searchQuery = text)
        mutableSearch.postValue(condition)
    }




}