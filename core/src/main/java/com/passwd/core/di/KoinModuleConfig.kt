package com.passwd.core.di

import androidx.lifecycle.LifecycleOwner
import org.koin.core.module.Module

data class KoinModuleConfig(
    var owner: LifecycleOwner?,
    val primaryScopeId: String,
    val primaryScopeName: String,
    val modules: List<Module>
)