package com.passwd.common.extension

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.workThread() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Maybe<T>.workThread() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun Completable.workThread() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
