package com.example.hiroshi.rxtubeapp.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created on 2018/03/09.
 */

/**
 * Base ViewModel
 * - handle Rx jobs with launch() and clear them on onCleared
 */
abstract class AbstractViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}