package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository

class SearchItemsViewModel(
        private val searchRepository: YoutubeSearchRepository, private val searchConditionRepository: YoutubeSearchConditionRepository
): ViewModel()  {

    // Input
    val activityOnCreate = MutableLiveData<Unit>()
    val refresh = MutableLiveData<Unit>()
    val selectedItem = MutableLiveData<>()

    // Output
    val searchItemDataSource: LiveData<SearchItemDataSource>

    init {

    }
}