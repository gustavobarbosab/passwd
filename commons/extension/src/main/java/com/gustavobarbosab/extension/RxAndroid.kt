package com.gustavobarbosab.extension

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.workIOThread() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.workIOThread() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.workIOThread() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
