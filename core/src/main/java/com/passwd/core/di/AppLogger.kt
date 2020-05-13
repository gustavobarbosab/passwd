package com.passwd.core.di

import android.util.Log
import com.passwd.core.BuildConfig

fun Any.logInfo(tag: String = this::class.java.name, msg: String) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

fun Any.logError(tag: String = this::class.java.name, msg: String) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}
