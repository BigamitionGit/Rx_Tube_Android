package com.example.hiroshi.rxtubeapp.data.network

import android.net.ConnectivityManager
import io.reactivex.Completable

/**
 * Created on 2018/03/12.
 */
class NetworkInteractorImpl (
        private val connectivityManager: ConnectivityManager
) : NetworkInteractor {

    override fun hasNetworkConnection(): Boolean =
            connectivityManager.activeNetworkInfo.isConnectedOrConnecting

    override fun hasNetworkConnectionCompletable(): Completable =
            if (hasNetworkConnection()) {
                Completable.complete()
            } else {
                Completable.error { NetworkInteractor.NetworkUnavailableException() }
            }

}