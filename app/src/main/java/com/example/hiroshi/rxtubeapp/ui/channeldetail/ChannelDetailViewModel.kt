package com.example.hiroshi.rxtubeapp.ui.channeldetail

import androidx.lifecycle.*
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchChannelDetail
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchVideoDetail
import io.reactivex.disposables.CompositeDisposable

class ChannelDetailViewModel: ViewModel(), LifecycleObserver {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // Output
    private val mutableShowPlayer: MutableLiveData<SearchVideoDetail> = MutableLiveData()
    val showPlayer: LiveData<SearchVideoDetail> = mutableShowPlayer

    // Input

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    fun channelDidTap(channel: SearchChannelDetail) {

    }
}