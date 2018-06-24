package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApi
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/03/14.
 */

private typealias RequireParameter = YoutubeApiParameter.Require.Search
private typealias RequireProperty = YoutubeApiParameter.Require.Search.Property
private typealias FilterParameter = YoutubeApiParameter.Filter.Search
private typealias OptionParameter = YoutubeApiParameter.Option.Search

interface YoutubeSearchRepository {

    fun getSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems>
}

class YoutubeSearchRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeSearchRepository {

    override fun getSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems> {
        val searchParameter:YoutubeApiParameter<RequireParameter, FilterParameter, OptionParameter> = YoutubeApiParameter(
                YoutubeApiParameter.Require.Search(setOf(RequireProperty.id, RequireProperty.snippet)),
                null,
                options)
        val searchApi = YoutubeApi.Search(searchParameter)
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = mapOf()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}