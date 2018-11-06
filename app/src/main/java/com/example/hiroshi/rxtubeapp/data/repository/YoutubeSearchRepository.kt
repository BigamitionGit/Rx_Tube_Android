package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.*
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Single

/**
 * Created on 2018/03/14.
 */

interface YoutubeSearchRepository {

    fun fetch(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems>
}

class YoutubeSearchRepositoryImpl(
        private val youtubeApiService: YoutubeApiService,
        private val networkInteractor: NetworkInteractor): YoutubeSearchRepository {

    override fun fetch(options: Set<YoutubeApiParameter.Option.Search>): Single<SearchItems> {
        val searchParameter = YoutubeApiParameter(
                YoutubeApiParameter.Require.Search(setOf(SearchRequire.ID, SearchRequire.SNIPPET)),
                null,
                options)
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = searchParameter.parameters))
    }
}