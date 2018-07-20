package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository
import io.reactivex.rxkotlin.toSingle
import io.reactivex.subjects.PublishSubject

class SearchItemsViewModel(
        private val searchRepository: YoutubeSearchRepository,
        private val searchConditionRepository: YoutubeSearchConditionRepository
): ViewModel()  {


    // Output
    private val mutableSearchItemAdapter: MutableLiveData<SearchItemsAdapter> = MutableLiveData()
    val searchItemAdapter: LiveData<SearchItemsAdapter> = mutableSearchItemAdapter


    // Input

    fun activityOnCreate() {

    }

    fun search(condition: YoutubeSearchCondition) {

    }

    fun refresh() {

    }


}