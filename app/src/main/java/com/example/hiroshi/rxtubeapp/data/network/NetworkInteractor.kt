package com.example.hiroshi.rxtubeapp.data.network

import io.reactivex.Completable

/**
 * Created on 2018/03/09.
 */
interface NetworkInteractor {

    fun hasNetworkConnection(): Boolean

    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException : Throwable("No network available!")
}