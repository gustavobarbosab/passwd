package com.gustavobarbosab.clearedpresenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

open class PresenterAutoCleared<VIEW : ViewObservable>(
    var view: VIEW? = null
) {

    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            onCleared()
        }
    }

    init {
        view?.lifecycle?.addObserver(observer)
    }

    open fun onCleared() {
        view?.lifecycle?.removeObserver(observer)
        view = null
    }
}