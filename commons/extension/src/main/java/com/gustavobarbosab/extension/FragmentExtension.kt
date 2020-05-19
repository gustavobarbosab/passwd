package com.gustavobarbosab.extension

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun <T> Fragment.getNavigationResult(key: String = "result") =
    findNavController()
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(key)

fun Fragment.setNavigationResult(key: String = "result", result: String) {
    findNavController()
        .previousBackStackEntry
        ?.savedStateHandle
        ?.set(key, result)
}