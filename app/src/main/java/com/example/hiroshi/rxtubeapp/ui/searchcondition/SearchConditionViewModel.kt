package com.example.hiroshi.rxtubeapp.ui.searchcondition

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchChannelDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchPlaylistDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemViewModel
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsAdapter
import io.reactivex.disposables.CompositeDisposable

class SearchConditionViewModel(
        private val searchConditionRepository: YoutubeSearchConditionRepository,
        private val searchConditionHistoryTranslator: SearchConditionHistoryTranslator
): ViewModel() {

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
    val mutableSearchHistory = MutableLiveData<YoutubeSearchCondition>()
    val searchHistory: LiveData<List<String>> = Transformations.map(mutableSearchHistory, { searchConditionHistoryTranslator.translate(it) })

    // Search
    private val mutableSearch = MutableLiveData<YoutubeSearchCondition>()
    val search: LiveData<YoutubeSearchCondition> = mutableSearch

    fun tapVideoType() {
        isVideoTypeSelected.value?.let { isVideoTypeSelected.postValue(!it) }
    }

    fun tapChannelType() {
        isChannelTypeSelected.value?.let { isChannelTypeSelected.postValue(!it) }
    }

    fun tapPlaylistType() {
        isPlaylistTypeSelected.value?.let { isPlaylistTypeSelected.postValue(!it) }
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