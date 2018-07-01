package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiParameter
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.YoutubeApiService
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItemDetails
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItemId
import com.example.hiroshi.rxtubeapp.data.remote.model.SearchItems
import io.reactivex.Single

/**
 * Created on 2018/03/14.
 */

private typealias RequireParameter = YoutubeApiParameter.Require.Search
private typealias RequireProperty = YoutubeApiParameter.Require.Search.Property
private typealias FilterParameter = YoutubeApiParameter.Filter.Search
private typealias OptionParameter = YoutubeApiParameter.Option.Search

interface YoutubeSearchRepository {

    fun fetchSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems>

    fun fetchSearchItemDetails(ids: Array<Pair<SearchItemId, String>>): Single<SearchItemDetails>
}

class YoutubeSearchRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeSearchRepository {

    override fun fetchSearchItems(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems> {
        val searchParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Search(setOf(RequireProperty.id, RequireProperty.snippet)),
                null,
                options)
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = searchParameter.parameters))
    }

    override fun fetchSearchItemDetails(ids: Array<Pair<SearchItemId, String>>): Single<SearchItemDetails> {

    }


}