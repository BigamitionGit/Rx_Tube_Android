package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import com.example.hiroshi.rxtubeapp.ui.searchitems.toResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/03/14.
 */

interface YoutubeSearchRepository {
    fun getSearchItemList(): Single<SearchItems>
}

class YoutubeSearchRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor) {

    fun getSearchItemList(): Single<SearchItems> {
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.itemSearch(part = "", i = mapOf(), b = mapOf(), s = mapOf(), d = mapOf()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}