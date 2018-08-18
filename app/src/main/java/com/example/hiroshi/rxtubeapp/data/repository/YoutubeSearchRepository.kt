package com.example.hiroshi.rxtubeapp.data.repository


import com.example.hiroshi.rxtubeapp.data.network.NetworkInteractor
import com.example.hiroshi.rxtubeapp.data.remote.apiservice.*
import com.example.hiroshi.rxtubeapp.data.remote.model.*
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.toSingle

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
                YoutubeApiParameter.Require.Search(setOf(SearchRequire.id, SearchRequire.snippet)),
                null,
                options)
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(youtubeApiService.search(parameter = searchParameter.parameters))
    }
}