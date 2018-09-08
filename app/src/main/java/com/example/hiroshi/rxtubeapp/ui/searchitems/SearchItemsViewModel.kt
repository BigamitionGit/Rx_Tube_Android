package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.arch.lifecycle.*
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchConditionRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchDetailRepository
import com.example.hiroshi.rxtubeapp.data.repository.YoutubeSearchRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toSingle
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class SearchItemsViewModel(
        private val searchRepository: YoutubeSearchRepository,
        private  val searchDetailRepository: YoutubeSearchDetailRepository,
        private val searchConditionRepository: YoutubeSearchConditionRepository,
        private val searchItemsAdapterTranslator: SearchItemsAdapterTranslator
): ViewModel(), LifecycleObserver  {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // Output
    private val mutableSearchItemDetails: MutableLiveData<SearchItemDetails> = MutableLiveData()
    val searchItemAdapter: LiveData<SearchItemsAdapter> = Transformations.map(mutableSearchItemDetails, { searchItemsAdapterTranslator.translate(it) })

    // Input

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

        searchConditionRepository.fetchSearchConditionHistory()
                .flatMapSingle { searchRepository.fetch(it.history.first().toYoutubeSearchOptionParameter()) }
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.postValue(it) })
                .addTo(compositeDisposable)
    }

    fun search(condition: YoutubeSearchCondition) {

        searchRepository.fetch(condition.toYoutubeSearchOptionParameter())
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.postValue(it) })
                .addTo(compositeDisposable)
    }

    fun refresh() {

        searchConditionRepository.fetchSearchConditionHistory()
                .flatMapSingle { searchRepository.fetch(it.history.first().toYoutubeSearchOptionParameter()) }
                .map { i: SearchItems -> i.items.map { Pair(it.id, it.snippet.channelId) } }
                .flatMap { searchDetailRepository.fetch(it) }
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = { mutableSearchItemDetails.postValue(it) })
                .addTo(compositeDisposable)
    }


}